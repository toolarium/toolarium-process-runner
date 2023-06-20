/*
 * ProcessMonitorServiceImpl.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.monitor.impl;

import com.github.toolarium.process.runner.dto.IProcessRuntimeEnvironment;
import com.github.toolarium.process.runner.dto.action.IProcessRunnerAction.Action;
import com.github.toolarium.process.runner.dto.action.ProcessRunnerAction;
import com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.IProcessInformation;
import com.github.toolarium.process.runner.monitor.ProcessMonitorService;
import com.github.toolarium.process.runner.monitor.impl.action.ProcessRunnerMonitorAction;
import com.github.toolarium.process.runner.monitor.impl.action.ProcessRunnerStartStopAction;
import com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implements the {@link ProcessMonitorService}.
 *   
 * @author patrick
 */
public class ProcessMonitorServiceImpl implements ProcessMonitorService {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessMonitorServiceImpl.class);
    private transient volatile IProcessRunnerDataStorage processRunnerData;
    private ScheduledExecutorService monitorExecutorService;
    private ScheduledExecutorService startStopExecutorService;
    private long initialDelay = 0;
    private long period = 1;
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    
    /**
     * @see com.github.toolarium.process.runner.monitor.ProcessMonitorService#init(com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage)
     * @param processRunnerDataStorage the process runner data storage
     * @throws IllegalStateException In case of invalid process runner data 
     */
    public void init(IProcessRunnerDataStorage processRunnerDataStorage) {
        verifyStorage();

        this.processRunnerData = processRunnerData;
        monitorExecutorService = null;
        startStopExecutorService = null;
        
        Runtime.getRuntime().addShutdownHook(new Thread(ProcessMonitorServiceImpl.class.getName() + ": Shutdown hook") {
            /**
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                try {
                    stopProcessMonitor();
                } catch (Exception e) {
                    // NOP
                }
            }
        });
    }


    /**
     * @see com.github.toolarium.process.runner.monitor.ProcessMonitorService#registerProcess(com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation, com.github.toolarium.process.runner.dto.process.IProcessInformation)
     */
    @Override
    public UUID registerProcess(IProcessInformation processInformation, IProcessMonitorInformation processRunnerMonitor) {
        verifyStorage();
        
        LOG.debug("Register new process [" + processInformation.getName() + "] on port [" + processInformation.getPort() + "] with resource [" + processInformation.getResourcePath() + "]");
        IProcessRuntimeEnvironment processRuntimeEnvironment = processRunnerData.add(processInformation, processRunnerMonitor);
        
        LOG.debug("Send start action to queue for [" + processRuntimeEnvironment.getUUID() + "]...");
        Runnable offerTask = () -> processRunnerData.getQueue().offer(new ProcessRunnerAction(processRuntimeEnvironment.getUUID(), Action.START));
        startStopExecutorService.submit(offerTask);
        
        return processRuntimeEnvironment.getUUID();
    }


    /**
     * @see com.github.toolarium.process.runner.monitor.ProcessMonitorService#unrregisterProcess(java.util.UUID)
     */
    @Override
    public void unregisterProcess(UUID uuid) {
        verifyStorage();

        LOG.debug("Send stop action to queue for [" + uuid + "]...");
        Runnable offerTask = () -> processRunnerData.getQueue().offer(new ProcessRunnerAction(uuid, Action.STOP));
        startStopExecutorService.submit(offerTask);
        
        IProcessRuntimeEnvironment processRuntimeEnvironment = processRunnerData.remove(uuid);
        if (processRuntimeEnvironment != null) {
            IProcessInformation processInformation = processRuntimeEnvironment.getProcessInformation();
            LOG.debug("Unregister process [" + processInformation.getName() + "] on port [" + processInformation.getPort() + "] with resource [" + processInformation.getResourcePath() + "] -> as [" + uuid + "]");
            return;
        }
    }


    /**
     * @see com.github.toolarium.process.runner.monitor.ProcessMonitorService#startProcessMonitor()
     */
    @Override
    public void startProcessMonitor() {        
        verifyStorage();

        initStartStopExecutorService();
        initMonitorExecutorService();
    }


    /**
     * @see com.github.toolarium.process.runner.monitor.ProcessMonitorService#stopProcessMonitor()
     */
    @Override
    public void stopProcessMonitor() {
        verifyStorage();
        
        finalizeMonitorExecutorService();
        finalizeStartStopExecutorService();
    }
    

    /**
     * @see com.github.toolarium.process.runner.monitor.ProcessMonitorService#setMonitorTimeSetting(long, long, java.util.concurrent.TimeUnit)
     */
    @Override
    public void setMonitorTimeSetting(long initialDelay, long period, TimeUnit timeUnit) {
        this.initialDelay = initialDelay;
        this.period = period;
        this.timeUnit = timeUnit;
    }
    
    
    /**
     * Initialise the start/stop executer service
     */
    protected void initStartStopExecutorService() {
        if (startStopExecutorService == null || !startStopExecutorService.isTerminated()) {
            startStopExecutorService = Executors.newScheduledThreadPool(1);
            startStopExecutorService.scheduleAtFixedRate(new ProcessRunnerStartStopAction(processRunnerData), initialDelay, period, timeUnit);
        }        
    }

    
    /**
     * Finalise the start/stop executer service
     */
    protected void finalizeStartStopExecutorService() {
        if (startStopExecutorService != null) {
            startStopExecutorService.shutdown();
            
            while (true) {
                try {
                    LOG.debug("Waiting for the start/stop service to terminate...");
                    if (startStopExecutorService.awaitTermination(2 * period, timeUnit)) {
                        break;
                    }
                } catch (InterruptedException e) {
                    // NOP
                }
            }
        }

    }

    
    /**
     * Initialise the monitor executer service
     */
    protected void initMonitorExecutorService() {
        if (monitorExecutorService == null || !monitorExecutorService.isTerminated()) {
            monitorExecutorService = Executors.newScheduledThreadPool(1);
            monitorExecutorService.scheduleAtFixedRate(new ProcessRunnerMonitorAction(processRunnerData), initialDelay, period, timeUnit);
        }
    }

    
    /**
     * Finalise the monitor executer service
     */
    protected void finalizeMonitorExecutorService() {
        
        if (monitorExecutorService != null) {
            monitorExecutorService.shutdown();
            
            /* we don't wait for the monitorExecutorService
            while (true) {
                try {
                    LOG.debug("Waiting for the monitor service to terminate...");
                    if (monitorExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                        break;
                    }
                } catch (InterruptedException e) {
                    // NOP
                }
            }
            */
        }
    }


    /**
     * Verify storage
     * 
     * @throws IllegalStateException In case of invalid process runner data 
     */
    protected void verifyStorage() {
        if (processRunnerData == null) {
            throw new IllegalStateException("Invalid pricess runner data!");
        }
    }
}

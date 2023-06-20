/*
 * StartStopRunnable.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.monitor.impl.action;

import com.github.toolarium.process.runner.dto.action.IProcessRunnerAction;
import com.github.toolarium.process.runner.dto.environment.IProcessEnvironment;
import com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.IProcessInformation;
import com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage;
import com.github.toolarium.process.runner.util.CommandUtil;
import com.github.toolarium.system.command.SystemCommandExecuterFactory;
import com.github.toolarium.system.command.process.IAsynchronousProcess;
import com.github.toolarium.system.command.process.stream.IProcessInputStream;
import com.github.toolarium.system.command.process.stream.ProcessStreamFactory;
import com.github.toolarium.system.command.process.stream.output.ProcessBufferOutputStream;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Defines the start / stop action
 * 
 * @author patrick
 */
public class ProcessRunnerStartStopAction extends AbstractProcessRunnerAction {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessRunnerStartStopAction.class);

    
    /**
     * Constructor for ProcessRunnerStartStopAction
     *
     * @param processRunnerDataStorage the process runner data storage
     */
    public ProcessRunnerStartStopAction(IProcessRunnerDataStorage processRunnerDataStorage) {
        super(processRunnerDataStorage);
    }
    
    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            while (getQueue().peek() != null) {
                IProcessRunnerAction processAction = getQueue().poll();
                LOG.info("Process " + processAction.getUUID() + "] should [" + processAction.getAction() + "]...");
                switch (processAction.getAction()) {
                    case START:
                        createProcess(processAction.getUUID());
                        break;
                    case STOP:
                    default:
                        stopProcess(processAction.getUUID());
                        break;
                }
            }
        } catch (RuntimeException e) {
            LOG.info("Interupt process monitor thread...");
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Create a process
     *
     * @param uuid the process reference
     */
    protected void stopProcess(UUID uuid) {
        IProcessInformation processInformation = getProcessRunnerDataStorage().getProcessInformation(uuid);
        //processInformation.getProcessEnvironment().get
        
        
    }
    
    /**
     * Create process
     * 
     * @param uuid the process reference
     */
    private void createProcess(UUID uuid) {
        IProcessInformation processInformation = getProcessRunnerDataStorage().getProcessInformation(uuid);
        
        processInformation.getName();
        processInformation.getPort();

        if (processInformation.hasResource()) {
            processInformation.getResourcePath();
            
        }
         

        if (processInformation.hasHealthCheck()) {
            processInformation.getHealthPath();
            
        }

        if (processInformation.hasBasicAuthentication()) {
            processInformation.getBasicAuthentication();
            
        }
        
        

        //IProcessEnvironment initProcessEnvironment = processInformation.getInitProcessEnvironment();
        if (processInformation.getInitProcessEnvironment() != null) {
            com.github.toolarium.system.command.dto.env.IProcessEnvironment processEnvironment = CommandUtil.getInstance().prepareProcessEnvironment(processInformation.getInitProcessEnvironment());
            //com.github.toolarium.system.command.dto.ISystemCommand systemCommand = CommandUtil.getInstance().prepareSystemCommand(processInformation.getInitProcessEnvironment());
            
            IProcessInputStream processInputStream = ProcessStreamFactory.getInstance().getStandardIn();
            ProcessBufferOutputStream output = ProcessStreamFactory.getInstance().getProcessBufferOutputStream();
            ProcessBufferOutputStream errOutput = ProcessStreamFactory.getInstance().getProcessBufferOutputStream();
            IAsynchronousProcess process = SystemCommandExecuterFactory.builder()
                .system().command(processInformation.getInitProcessEnvironment().getCommand())
                .build()
                .runAsynchronous(processInputStream, output, errOutput);
                       
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                // TODO:
                //LOGGER.warn("Error occured: " + e.getMessage(), e);
            }
        }

        
        if (processInformation.getProcessEnvironment() != null) {
            com.github.toolarium.system.command.dto.env.IProcessEnvironment processEnvironment = CommandUtil.getInstance().prepareProcessEnvironment(processInformation.getProcessEnvironment());
            //com.github.toolarium.system.command.dto.ISystemCommand systemCommand = CommandUtil.getInstance().prepareSystemCommand(processInformation.getProcessEnvironment());
            
            IProcessInputStream processInputStream = ProcessStreamFactory.getInstance().getStandardIn();
            ProcessBufferOutputStream output = ProcessStreamFactory.getInstance().getProcessBufferOutputStream();
            ProcessBufferOutputStream errOutput = ProcessStreamFactory.getInstance().getProcessBufferOutputStream();
            IAsynchronousProcess process = SystemCommandExecuterFactory.builder()
                .system().command(processInformation.getInitProcessEnvironment().getCommand())
                .build()
                .runAsynchronous(processInputStream, output, errOutput);
            
            // register
        }
        
        IProcessEnvironment pe2 = processInformation.getProcessEnvironment();

        
        IProcessMonitorInformation processRunnerMonitor = getProcessRunnerDataStorage().getProcessMonitorInformation(uuid);
        processRunnerMonitor.getNumberOfReplicas();
        processRunnerMonitor.getLivenessProbeInformation();
        processRunnerMonitor.getReadinessProbeInformation();
        processRunnerMonitor.getStartupProbeInformation();
        processRunnerMonitor.getRestartPolicy();
    }
}

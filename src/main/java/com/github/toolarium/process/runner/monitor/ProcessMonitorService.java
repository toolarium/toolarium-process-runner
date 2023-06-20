/*
 * IProcessMonitor.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.monitor;

import com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.IProcessInformation;
import com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Monitor the registered processes.
 * 
 * @author patrick
 */
public interface ProcessMonitorService {

    /**
     * Initialize the process monitor service
     *
     * @param processRunnerDataStorage the process runner data storage
     */
    void init(IProcessRunnerDataStorage processRunnerDataStorage);
    
    
    /**
     * Register process
     * 
     * @param processRunnerMonitor the process monitor
     * @param processInformation the process information
     * @return a unique process identifier
     */
    UUID registerProcess(IProcessInformation processInformation, IProcessMonitorInformation processRunnerMonitor);
    
    
    /**
     * Unregister a process
     *
     * @param uuid the uuid
     */
    void unregisterProcess(UUID uuid);

    
    /**
     * Start the process monitor. It can be stopped and restarted without having impact on started processes.
     * By default the process monitor not started. It must be called to start the monitoring. 
     */
    void startProcessMonitor();

    
    /**
     * Stop the process monitor. It can be stopped and restarted without having impact on started processes.
     * In case it's stopped no process monitoring is secured.
     */
    void stopProcessMonitor();

    
    /**
     * Set the monitor time settings
     *
     * @param initialDelay the initial delay
     * @param period the start period
     * @param timeUnit the time unit
     */
    void setMonitorTimeSetting(long initialDelay, long period, TimeUnit timeUnit);
    
}

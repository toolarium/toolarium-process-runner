/*
 * IProcessRunnerDataStorage.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.storage;

import com.github.toolarium.process.runner.dto.IProcessRuntimeEnvironment;
import com.github.toolarium.process.runner.dto.action.IProcessRunnerAction;
import com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.IProcessInformation;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;


/**
 * Defines the process runner data.
 * 
 * @author patrick
 */
public interface IProcessRunnerDataStorage {

    /**
     * Add a new process runtime environment
     * 
     * @param processInformation the process information
     * @param processMonitorInformation the process monitor information 
     * @return the process runtime environment
     */
    IProcessRuntimeEnvironment add(IProcessInformation processInformation, IProcessMonitorInformation processMonitorInformation);

    
    /**
     * Unregister the process runtime environment
     * 
     * @param uuid the uuid reference
     * @return the removed process runtime environment
     */
    IProcessRuntimeEnvironment remove(UUID uuid);

    
    /**
     * Get the process runner action queue
     *
     * @return the process runner action queue
     */
    Queue<IProcessRunnerAction> getQueue();
    
    
    /**
     * Get the process list
     *
     * @return the process list
     */
    Set<UUID> getProcessList();
    
    
    /**
     * Get the process information
     *
     * @param uuid the uuid
     * @return the process information
     */
    IProcessInformation getProcessInformation(UUID uuid);

    
    /**
     * Get the process monitor information
     *
     * @param uuid the uuid
     * @return the process monitor information 
     */
    IProcessMonitorInformation getProcessMonitorInformation(UUID uuid);

}

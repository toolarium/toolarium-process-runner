/*
 * IProcessRuntimeEnvironment.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto;

import com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.IProcessInformation;
import java.util.UUID;


/**
 * Defines the process runtime environment
 * 
 * @author patrick
 */
public interface IProcessRuntimeEnvironment {
    
    /**
     * Get the UUID. 
     *
     * @return the UUID.
     */
    UUID getUUID();
    
    
    /**
     * Get the process information
     *
     * @return the process information
     */
    IProcessInformation getProcessInformation();
    
    
    /**
     * Get the process monitor information
     *
     * @return the process monitor information
     */
    IProcessMonitorInformation getProcessMonitorInformation();
    
}

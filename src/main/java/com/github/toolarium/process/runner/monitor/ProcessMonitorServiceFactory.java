/*
 * ProcessMonitorServiceFactory.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.monitor;

import com.github.toolarium.process.runner.monitor.impl.ProcessMonitorServiceImpl;


/**
 * Defines the process monitor service factory
 *  
 * @author patrick
 */
public final class ProcessMonitorServiceFactory {

    
    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static class HOLDER {
        static final ProcessMonitorServiceFactory INSTANCE = new ProcessMonitorServiceFactory();
    }

    
    /**
     * Constructor
     */
    private ProcessMonitorServiceFactory() {
        // NOP
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static ProcessMonitorServiceFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    
    /**
     * Get the process monitor
     *
     * @return the process monitor
     */
    public ProcessMonitorService getProcessMonitorService() {        
        return new ProcessMonitorServiceImpl();
    }
}

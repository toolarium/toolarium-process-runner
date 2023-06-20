/*
 * ProcessRunnerDataStorageFactory.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.storage;

import com.github.toolarium.process.runner.storage.impl.ProcessRunnerDataStorageImpl;

/**
 * Defines the process runner data factory
 *  
 * @author patrick
 */
public final class ProcessRunnerDataStorageFactory {
    private IProcessRunnerDataStorage processRunnerData;
    
    
    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static class HOLDER {
        static final ProcessRunnerDataStorageFactory INSTANCE = new ProcessRunnerDataStorageFactory();
    }

    
    /**
     * Constructor
     */
    private ProcessRunnerDataStorageFactory() {
        processRunnerData = new ProcessRunnerDataStorageImpl();
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static ProcessRunnerDataStorageFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    
    /**
     * Get the process runner data
     *
     * @return the process runner data
     */
    public IProcessRunnerDataStorage getProcessRunnerData() {
        return processRunnerData;
    }
}

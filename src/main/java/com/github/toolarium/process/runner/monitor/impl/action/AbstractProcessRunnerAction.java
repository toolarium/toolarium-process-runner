/*
 * AbstractProcessRunnerAction.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.monitor.impl.action;

import com.github.toolarium.process.runner.dto.action.IProcessRunnerAction;
import com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage;
import java.util.Queue;


/**
 * Defines the abstract action base class.
 *  
 * @author patrick
 */
public abstract class AbstractProcessRunnerAction implements Runnable {
    private IProcessRunnerDataStorage processRunnerDataStorage;
    
    
    /**
     * Constructor for AbstractProcessRunnerAction
     *
     * @param processRunnerDataStorage the process runner storage
     */
    public AbstractProcessRunnerAction(IProcessRunnerDataStorage processRunnerDataStorage) {
        this.processRunnerDataStorage = processRunnerDataStorage;
    }


    /**
     * Gets the process runner data storage
     *
     * @return the process runner data storage
     */
    protected IProcessRunnerDataStorage getProcessRunnerDataStorage() {
        return processRunnerDataStorage;
    }

    
    /**
     * Gets the queue
     *
     * @return the queue
     */
    protected Queue<IProcessRunnerAction> getQueue() {
        return processRunnerDataStorage.getQueue();
    }
}

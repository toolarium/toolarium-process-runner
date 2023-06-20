/*
 * IProcessRunnerAction.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.action;

import java.util.UUID;

/**
 * Defines the process action
 * 
 * @author patrick
 */
public interface IProcessRunnerAction {
    
    /**
     * Get the UUID of the process
     *
     * @return the UUID
     */
    UUID getUUID();

    
    /**
     * Geth the action
     *
     * @return the action
     */
    Action getAction();

    
    /**
     * Defines the supported process actions
     */
    enum Action {
        START,
        STOP;
    }
}

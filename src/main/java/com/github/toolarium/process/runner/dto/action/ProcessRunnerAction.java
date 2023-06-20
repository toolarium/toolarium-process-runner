/*
 * ProcessRunnerAction.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.action;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * Implements the {@link IProcessRunnerAction}.
 * 
 * @author patrick
 */
public class ProcessRunnerAction implements IProcessRunnerAction, Serializable {
    private static final long serialVersionUID = -6254468170811092759L;
    private UUID uuid;
    private IProcessRunnerAction.Action action;
    
    
    /**
     * Constructor for ProcessRunnerAction
     *
     * @param uuid the uuid 
     * @param action the action
     */
    public ProcessRunnerAction(UUID uuid, IProcessRunnerAction.Action action) {
        this.uuid = uuid;
        this.action = action;
    }
    
    
    /**
     * @see com.github.toolarium.process.runner.dto.action.IProcessRunnerAction#getUUID()
     */
    @Override
    public UUID getUUID() {
        return uuid;
    }
    

    /**
     * @see com.github.toolarium.process.runner.dto.action.IProcessRunnerAction#getAction()
     */
    @Override
    public IProcessRunnerAction.Action getAction() {
        return action;
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(action, uuid);
    }


    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
            
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        ProcessRunnerAction other = (ProcessRunnerAction) obj;
        return action == other.action && Objects.equals(uuid, other.uuid);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProcessRunnerAction [uuid=" + uuid + ", action=" + action + "]";
    }
}

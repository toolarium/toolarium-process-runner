/*
 * ProcessRuntimeEnvironment.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto;

import com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.IProcessInformation;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * Implements the {@link IProcessRuntimeEnvironment}.
 *  
 * @author patrick
 */
public class ProcessRuntimeEnvironment implements IProcessRuntimeEnvironment, Serializable {
    private static final long serialVersionUID = 4292525077285399861L;
    private UUID uuid;
    private IProcessInformation processInformation;
    private IProcessMonitorInformation processMonitorInformation;

    
    /**
     * Constructor for ProcessRuntimeEnvironment
     *
     * @param uuid the uuid
     * @param processInformation the process information
     * @param processMonitorInformation the process monitor information
     */
    public ProcessRuntimeEnvironment(UUID uuid, IProcessInformation processInformation, IProcessMonitorInformation processMonitorInformation) {
        this.uuid = uuid;
        this.processInformation = processInformation;
        this.processMonitorInformation = processMonitorInformation;
    }
    
    
    /**
     * @see com.github.toolarium.process.runner.dto.IProcessRuntimeEnvironment#getUUID()
     */
    @Override
    public UUID getUUID() {
        return uuid;
    }
    

    /**
     * @see com.github.toolarium.process.runner.dto.IProcessRuntimeEnvironment#getProcessInformation()
     */
    @Override
    public IProcessInformation getProcessInformation() {
        return processInformation;
    }
    

    /**
     * @see com.github.toolarium.process.runner.dto.IProcessRuntimeEnvironment#getProcessMonitorInformation()
     */
    @Override
    public IProcessMonitorInformation getProcessMonitorInformation() {
        return processMonitorInformation;
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(processInformation, processMonitorInformation, uuid);
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
            
        ProcessRuntimeEnvironment other = (ProcessRuntimeEnvironment) obj;
        return Objects.equals(processInformation, other.processInformation)
                && Objects.equals(processMonitorInformation, other.processMonitorInformation)
                && Objects.equals(uuid, other.uuid);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProcessRuntimeEnvironment [uuid=" + uuid + ", processInformation=" + processInformation
                + ", processMonitorInformation=" + processMonitorInformation + "]";
    }
}

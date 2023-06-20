/*
 * ProcessMonitorInformation.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.monitor;

import java.io.Serializable;
import java.util.Objects;


/**
 * Implements the {@link IProcessMonitorInformation}.
 *  
 * @author patrick
 */
public class ProcessMonitorInformation implements IProcessMonitorInformation, Serializable {
    private static final long serialVersionUID = 8086429342197684162L;
    private int numberOfReplicas;
    private IProcessMonitoreProbeInformation startupProbeInformation;
    private IProcessMonitoreProbeInformation livenessProbeInformation;
    private IProcessMonitoreProbeInformation readinessProbeInformation;
    private RestartPolicy restartPolicy;
    
    
    /**
     * Constructor for ProcessMonitorInformation
     */
    public ProcessMonitorInformation() {
        numberOfReplicas = 1;
        startupProbeInformation = null;
        livenessProbeInformation = new ProcessMonitoreProbeInformation();
        readinessProbeInformation = new ProcessMonitoreProbeInformation();
        restartPolicy = RestartPolicy.ALWAYS;
    }
    
    
    /**
     * Constructor for ProcessMonitorInformation
     *
     * @param processMonitorInformation the process monitor information
     */
    public ProcessMonitorInformation(IProcessMonitorInformation processMonitorInformation) {
        this.numberOfReplicas = processMonitorInformation.getNumberOfReplicas();
        this.startupProbeInformation = processMonitorInformation.getStartupProbeInformation();
        this.livenessProbeInformation = processMonitorInformation.getLivenessProbeInformation();
        this.readinessProbeInformation = processMonitorInformation.getReadinessProbeInformation();
        this.restartPolicy = processMonitorInformation.getRestartPolicy();
    }
    

    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation#getNumberOfReplicas()
     */
    @Override
    public int getNumberOfReplicas() {
        return numberOfReplicas;        
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation#getStartupProbeInformation()
     */
    @Override
    public IProcessMonitoreProbeInformation getStartupProbeInformation() {
        return startupProbeInformation;
    }

    
    /**
     * Sets the startup probe information
     *
     * @param startupProbeInformation the startup probe information 
     * @return the process runner monitor
     */
    public ProcessMonitorInformation setStartupProbe(IProcessMonitoreProbeInformation startupProbeInformation) {
        this.startupProbeInformation = startupProbeInformation;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation#getLivenessProbeInformation()
     */
    @Override
    public IProcessMonitoreProbeInformation getLivenessProbeInformation() {
        return livenessProbeInformation;
    }

    
    /**
     * Sets the liveness probe information
     *
     * @param livenessProbeInformation the liveness probe information 
     * @return the process runner monitor
     */
    public ProcessMonitorInformation setLivenessProbe(IProcessMonitoreProbeInformation livenessProbeInformation) {
        this.livenessProbeInformation = livenessProbeInformation;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation#getReadinessProbeInformation()
     */
    @Override
    public IProcessMonitoreProbeInformation getReadinessProbeInformation() {
        return readinessProbeInformation;
    }

    
    /**
     * Sets the readiness probe information
     *
     * @param readinessProbeInformation the readiness probe information 
     * @return the process runner monitor
     */
    public ProcessMonitorInformation setReadinessProbe(IProcessMonitoreProbeInformation readinessProbeInformation) {
        this.readinessProbeInformation = readinessProbeInformation;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation#getRestartPolicy()
     */
    @Override
    public RestartPolicy getRestartPolicy() {
        return restartPolicy;
    }

    
    /**
     * Sets the restart policy
     *
     * @param restartPolicy the restart policy 
     * @return the process runner monitor
     */
    public ProcessMonitorInformation setRestartPolicy(RestartPolicy restartPolicy) {
        this.restartPolicy = restartPolicy;
        return this;
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(numberOfReplicas, livenessProbeInformation, readinessProbeInformation, restartPolicy, startupProbeInformation);
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
        
        ProcessMonitorInformation other = (ProcessMonitorInformation) obj;
        return numberOfReplicas == other.numberOfReplicas 
                && Objects.equals(livenessProbeInformation, other.livenessProbeInformation)
                && Objects.equals(readinessProbeInformation, other.readinessProbeInformation) && restartPolicy == other.restartPolicy
                && Objects.equals(startupProbeInformation, other.startupProbeInformation);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProcessMonitorInformation [numberOfReplicas=" + numberOfReplicas 
                + ", startupProbeInformation=" + startupProbeInformation + ", livenessProbeInformation=" + livenessProbeInformation
                + ", readinessProbeInformation=" + readinessProbeInformation + ", restartPolicy=" + restartPolicy + "]";
    }
}

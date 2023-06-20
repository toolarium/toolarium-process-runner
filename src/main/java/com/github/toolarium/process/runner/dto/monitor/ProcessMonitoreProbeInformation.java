/*
 * ProcessMonitoreProbeInformation.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.monitor;

import java.io.Serializable;
import java.util.Objects;


/**
 * Implements the {@link IProcessMonitoreProbeInformation}.
 * 
 * @author patrick
 */
public class ProcessMonitoreProbeInformation implements IProcessMonitoreProbeInformation, Serializable {
    private static final long serialVersionUID = 2211776296714193835L;
    private int failureThreshold;
    private int initialDelaySeconds;
    private int periodSeconds;
    private int successThreshold;
    private int timeoutSeconds;

    
    /**
     * Constructor for ProcessMonitoreProbe
     */
    public ProcessMonitoreProbeInformation() {
        failureThreshold = 1;
        initialDelaySeconds = 0;
        periodSeconds = 10;
        successThreshold = 1;
        timeoutSeconds = 1;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitoreProbeInformation#getFailureThreshold()
     */
    @Override
    public int getFailureThreshold() {
        return failureThreshold;
    }

    
    /**
     * Sets the failure threshold
     *
     * @param failureThreshold the failure threshold 
     * @return the process monitor probe
     * @throws IllegalArgumentException in case of an invalid value
     */
    public ProcessMonitoreProbeInformation setFailureThreshold(int failureThreshold) {
        if (initialDelaySeconds < 1) {
            throw new IllegalArgumentException();
        }
        
        this.failureThreshold = failureThreshold;
        return this;
    }
    
    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitoreProbeInformation#getInitialDelaySeconds()
     */
    @Override
    public int getInitialDelaySeconds() {
        return initialDelaySeconds;
    }

    
    /**
     * Sets the initial delay
     *
     * @param initialDelaySeconds the initial delay 
     * @return the process monitor probe
     * @throws IllegalArgumentException in case of an invalid value
     */
    public ProcessMonitoreProbeInformation setInitialDelaySeconds(int initialDelaySeconds) {
        if (initialDelaySeconds < 0) {
            throw new IllegalArgumentException();
        }
        
        this.initialDelaySeconds = initialDelaySeconds;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitoreProbeInformation#getPeriodSeconds()
     */
    @Override
    public int getPeriodSeconds() {
        return periodSeconds;
    }

    
    /**
     * Sets the period seconds
     *
     * @param periodSeconds the period seconds 
     * @return the process monitor probe
     * @throws IllegalArgumentException in case of an invalid value
     */
    public ProcessMonitoreProbeInformation setPeriodSeconds(int periodSeconds) {
        if (initialDelaySeconds < 1) {
            throw new IllegalArgumentException();
        }
        
        this.periodSeconds = periodSeconds;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitoreProbeInformation#getSuccessThreshold()
     */
    @Override
    public int getSuccessThreshold() {
        return successThreshold;
    }

    
    /**
     * Sets the success threshold
     *
     * @param successThreshold the success threshold 
     * @return the process monitor probe
     * @throws IllegalArgumentException in case of an invalid value
     */
    public ProcessMonitoreProbeInformation setSuccessThreshold(int successThreshold) {
        if (initialDelaySeconds < 1) {
            throw new IllegalArgumentException();
        }
        
        this.successThreshold = successThreshold;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.monitor.IProcessMonitoreProbeInformation#getTimeoutSeconds()
     */
    @Override
    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }
    
    
    /**
     * Sets the timeout seconds
     *
     * @param timeoutSeconds the timeout seconds 
     * @return the process monitor probe
     * @throws IllegalArgumentException in case of an invalid value
     */
    public ProcessMonitoreProbeInformation setTimeoutSeconds(int timeoutSeconds) {
        if (initialDelaySeconds < 1) {
            throw new IllegalArgumentException();
        }
        
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(failureThreshold, initialDelaySeconds, periodSeconds, successThreshold, timeoutSeconds);
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
        
        ProcessMonitoreProbeInformation other = (ProcessMonitoreProbeInformation) obj;
        return failureThreshold == other.failureThreshold && initialDelaySeconds == other.initialDelaySeconds
                && periodSeconds == other.periodSeconds && successThreshold == other.successThreshold
                && timeoutSeconds == other.timeoutSeconds;
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProcessMonitoreProbeInformation [failureThreshold=" + failureThreshold + ", initialDelaySeconds="
                + initialDelaySeconds + ", periodSeconds=" + periodSeconds + ", successThreshold=" + successThreshold
                + ", timeoutSeconds=" + timeoutSeconds + "]";
    }
}

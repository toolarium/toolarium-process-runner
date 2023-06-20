/*
 * IProcessMonitoreProbeInformation.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.monitor;

/**
 * Defines the process monitor probe information
 * 
 * @author patrick
 */
public interface IProcessMonitoreProbeInformation {

    /**
     * Get the failure threshold, default is 1.
     *
     * @return the failure threshold
     */
    int getFailureThreshold();

    
    /**
     * Get the initial delay seconds which has to be wait after startup.
     * Number of seconds after startup. Defaults to 0 seconds. Minimum value is 0.
     *
     * @return the initial delay seconds
     */
    int getInitialDelaySeconds();
    
    
    /**
     * Get the period seconds, every n seconds a probe will be evaluated. 
     * How often (in seconds) to perform the probe. Default to 10 seconds. Minimum value is 1.
     *
     * @return the period seconds
     */
    int getPeriodSeconds();
    
    
    /**
     * Get the success threshold. Minimum consecutive successes for the probe to be considered successful after having failed. 
     * Defaults to 1. The minimum value is 1.
     *
     * @return the success threshold
     */
    int getSuccessThreshold();
    
    
    /**
     * Get the timeout seconds.
     * Number of seconds after which the probe times out. Defaults to 1 second. Minimum value is 1.
     *
     * @return the timeout seconds
     */
    int getTimeoutSeconds();
    
}

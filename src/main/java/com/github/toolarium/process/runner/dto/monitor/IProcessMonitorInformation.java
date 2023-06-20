/*
 * IProcessMonitorInformation.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.monitor;

/**
 * Defines the process monitor information
 * 
 * @author patrick
 */
public interface IProcessMonitorInformation {

    
    /**
     * Get the number of replicas
     *
     * @return the number of replicas
     */
    int getNumberOfReplicas();
    
    
    /**
     * Used to postpone probes until startup phase information.
     *
     * @return the probe information
     */
    IProcessMonitoreProbeInformation getStartupProbeInformation();

    
    /**
     * Used to check if the process is available and alive. 
     *
     * @return the probe information
     */
    IProcessMonitoreProbeInformation getLivenessProbeInformation();

    
    /**
     * Used to check if the application is ready to use and serve the traffic.
     *
     * @return the probe information
     */
    IProcessMonitoreProbeInformation getReadinessProbeInformation();


    /**
     * Get the restart policy
     *
     * @return the restart policy
     */
    RestartPolicy getRestartPolicy();
    

    /**
     * Defines the restart policy
     */
    enum RestartPolicy {
        /** Never means that it will not be restarted regardless of why it exited. */
        NEVER,
        
        /** On failure means that it will only be restarted if it exited with a non-zero exit code (i.e. something went wrong). */
        ONFAILURE,
        
        /** Always means that it will be restarted even if it exited with a zero exit code (i.e. successfully). This is the default.*/
        ALWAYS;
    }
}

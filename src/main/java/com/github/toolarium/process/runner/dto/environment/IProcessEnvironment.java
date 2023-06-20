/*
 * IProcessEnvironment.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.environment;

import com.github.toolarium.process.runner.dto.base.IProcessProperties;
import com.github.toolarium.process.runner.dto.base.ISecuredValue;
import java.util.List;


/**
 * Defines the process environment
 * 
 * @author patrick
 */
public interface IProcessEnvironment {

    /**
     * Get the system properties
     *
     * @return the system properties
     */
    IProcessProperties getSystemProperties();

    
    /**
     * Get the environment properties
     *
     * @return the environment properties
     */
    IProcessProperties getEnvironmentProperties();

    
    /**
     * Get the process parameters
     *
     * @return the process parameters
     */
    List<ISecuredValue<String>> getProcessParameters();

    
    /**
     * The command to run the process.
     *
     * @return the command
     */
    String getCommand();

    
    /**
     * Get the user.
     *
     * @return the user
     */
    String getUser();

    
    /**
     * Get the working path
     * 
     * @return the working path
     */
    String getWorkingPath();

    
    /**
     * Get the temp path
     * 
     * @return the temp path
     */
    String getTempPath();

    
    /**
     * Get the operating system
     *
     * @return the operating system
     */
    String getOS();
    
    
    /**
     * Get the operation system version
     *
     * @return the operation system version
     */
    String getOSVersion();
    
    
    /**
     * Get the device architecture
     *
     * @return the device architecture
     */
    String getArchitecture();
    
}

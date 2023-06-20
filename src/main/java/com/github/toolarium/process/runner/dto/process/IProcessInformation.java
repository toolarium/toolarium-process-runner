/*
 * IProcessInformation.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.process;

import com.github.toolarium.process.runner.dto.environment.IProcessEnvironment;

/**
 * Defines the process
 * 
 * @author patrick
 */
public interface IProcessInformation {
    
    /**
     * Get the process name
     *
     * @return the process name
     */
    String getName();

    
    /**
     * Get the port
     *
     * @return the port
     */
    int getPort();

    
    /**
     * Define if the process support resource  
     *
     * @return true if it is enabled
     */
    boolean hasResource();

    
    /**
     * Get the resource path  
     *
     * @return the resource path
     */
    String getResourcePath();

    
    /**
     * Define if the process support health  
     *
     * @return true if it is enabled
     */
    boolean hasHealthCheck();
    

    /**
     * Get the health path  
     *
     * @return the health path
     */
    String getHealthPath();

    
    /**
     * Define if the resource has basic authentication  
     *
     * @return true if it is enabled
     */
    boolean hasBasicAuthentication();

    
    /**
     * Get the basic authentication: user:password
     *
     * @return the basic authentication
     */
    String getBasicAuthentication();

    
    /**
     * Get the initialise process environment; it's optional and can be used to run initialise.
     *
     * @return the initialise process environment
     */
    IProcessEnvironment getInitProcessEnvironment();

    
    /**
     * Get the process environment
     *
     * @return the process environment
     */
    IProcessEnvironment getProcessEnvironment();
}

/*
 * IProcessConfiguration.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.config;

import java.util.Properties;

/**
 * 
 * @author patrick
 */
public interface IProcessConfiguration {

    /**
     * Get the environment variables for the process 
     *
     * @return the environment variables
     */
    Properties getEnvironmentVariables();
}

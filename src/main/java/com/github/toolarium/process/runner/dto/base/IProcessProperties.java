/*
 * IProcessProperties.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.base;

import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author patrick
 */
public interface IProcessProperties {

    /**
     * Get the key set
     *
     * @return the key set
     */
    Set<String> keySet();
    
    
    /**
     * Get a process property (secured)
     *
     * @param key the property key
     * @return the value
     */
    String getProprty(String key);

    
    /**
     * Get a process property
     *
     * @param key the property key
     * @param secured true if it must be secured
     * @return the value
     */
    String getProprty(String key, boolean secured);

    
    /**
     * Set a process property
     *
     * @param key the property key
     * @param value the value
     * @param secured true if it must be secured
     * @return the previous value
     */
    ISecuredValue<String> setProprty(String key, String value, boolean secured);

    
    /**
     * Set the properties
     *
     * @param properties the properties to set
     */
    void setProperties(Properties properties);
    
    
    /**
     * Get the properties
     *
     * @param secured true to secure properties which are marked as to be secured
     * @return the properties
     */
    Properties getProperties(boolean secured);
    
    
    /**
     * Check if there are no properties
     *
     * @return true if it is empty
     */
    boolean isEmpty();
}

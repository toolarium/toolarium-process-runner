/*
 * ProcessProperties.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.base;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implements the {@link IProcessProperties}.
 * 
 * @author patrick
 */
public class ProcessProperties implements IProcessProperties, Serializable {
    private static final long serialVersionUID = -855470136016452029L;
    private Map<String, ISecuredValue<String>> properties;

    
    /**
     * Constructor for ProcessProperties
     */
    public ProcessProperties() {
        setProperties(null);
    }

    
    
    /**
     * @see com.github.toolarium.process.runner.dto.base.IProcessProperties#keySet()
     */
    @Override
    public Set<String> keySet() {
        return properties.keySet();
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.base.IProcessProperties#getProprty(java.lang.String)
     */
    @Override
    public String getProprty(String key) {
        return getProprty(key, true);
    }
    

    /**
     * @see com.github.toolarium.process.runner.dto.base.IProcessProperties#getProprty(java.lang.String, boolean)
     */
    @Override
    public String getProprty(String key, boolean secured) {
        ISecuredValue<String> value = properties.get(key);
        if (value == null) {
            return null;
        }
        
        if (secured) {
            return value.getSecuredValue();
        }
        
        return value.getValue();
    }


    /**
     * @see com.github.toolarium.process.runner.dto.base.IProcessProperties#setProprty(java.lang.String, java.lang.String, boolean)
     */
    @Override
    public ISecuredValue<String> setProprty(String key, String value, boolean secured) {
        ISecuredValue<String> newValue = new SecuredValue<String>(value, "...");
        
        if (secured) {
            newValue = new SecuredValue<String>(value, "...");
        } else {
            newValue = new SecuredValue<String>(value, value);
        }
        
        return properties.put(key, newValue); 
    }


    /**
     * @see com.github.toolarium.process.runner.dto.base.IProcessProperties#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties properties) {
        if (properties == null) {
            this.properties = new ConcurrentHashMap<>();
            return;
        }
        
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            setProprty(key, properties.getProperty(key), false);
        }
    }


    /**
     * @see com.github.toolarium.process.runner.dto.base.IProcessProperties#getProperties(boolean)
     */
    @Override
    public Properties getProperties(boolean secured) {
        Properties prop = new Properties();
        
        for (Map.Entry<String, ISecuredValue<String>> entry : properties.entrySet()) {
            String value;
            if (entry.getValue().hasSecuredValue()) {
                if (secured) {
                    value = entry.getValue().getSecuredValue();
                } else {
                    value = entry.getValue().getValue();
                }
            } else {
                value = entry.getValue().getValue();
            }
            
            prop.setProperty(entry.getKey(), value);
        }
        
        return prop;
    }


    /**
     * @see com.github.toolarium.process.runner.dto.base.IProcessProperties#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return properties.isEmpty();
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(properties);
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
        
        ProcessProperties other = (ProcessProperties) obj;
        return Objects.equals(properties, other.properties);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProcessProperties [properties=" + properties + "]";
    }
}

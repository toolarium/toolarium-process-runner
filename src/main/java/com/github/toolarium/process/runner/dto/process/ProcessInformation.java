/*
 * ProcessInformation.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.process;

import com.github.toolarium.process.runner.dto.environment.IProcessEnvironment;
import java.io.Serializable;
import java.util.Objects;


/**
 * Implements {@link IProcessInformation}.
 * 
 * @author patrick
 */
public class ProcessInformation implements IProcessInformation, Serializable {
    private static final long serialVersionUID = 4051175418234541030L;
    private String name;
    private int port;
    private String resourcePath;
    private String healthPath;
    private String basicAuth;
    private IProcessEnvironment initProcessEnvironment;
    private IProcessEnvironment processEnvironment;


    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    
    /**
     * Set the process name
     *
     * @param name the process name
     * @return the process
     */
    public ProcessInformation setName(String name) {
        this.name = name;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#getPort()
     */
    @Override
    public int getPort() {
        return port;
    }


    /**
     * Set the port
     *
     * @param port the port
     * @return the process
     */
    public ProcessInformation setPort(int port) {
        this.port = port;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#hasResource()
     */
    @Override
    public boolean hasResource() {
        return (resourcePath != null && !resourcePath.isBlank());
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#getResourcePath()
     */
    @Override
    public String getResourcePath() {
        return resourcePath;
    }

    
    /**
     * Set the resource path
     *
     * @param resourcePath the resource path
     * @return the process
     */
    public ProcessInformation setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        return this;
    }

    
    /**
     * Define if the process support health  
     *
     * @return true if it is enabled
     */
    public boolean hasHealthCheck() {
        return (healthPath != null && !healthPath.isBlank());
    }
    

    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#getHealthPath()
     */
    @Override
    public String getHealthPath() {
        return healthPath;
    }

    
    /**
     * Set the health path
     *
     * @param healthPath the health path
     * @return the process
     */
    public ProcessInformation setHealthPath(String healthPath) {
        this.healthPath = healthPath;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#hasBasicAuthentication()
     */
    @Override
    public boolean hasBasicAuthentication() {
        return (basicAuth != null && !basicAuth.isBlank());
    }


    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#getBasicAuthentication()
     */
    @Override
    public String getBasicAuthentication() {
        return basicAuth;
    }

    
    /**
     * Set the basic auth
     *
     * @param user the user
     * @param password the password
     * @return the process
     */
    public ProcessInformation setBasicAuthentication(String user, String password) {
        this.basicAuth = user + ":" + password;
        return this;
    }


    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#getInitProcessEnvironment()
     */
    @Override
    public IProcessEnvironment getInitProcessEnvironment() {
        return initProcessEnvironment;
    }

    
    /**
     * Set the initialise process environment
     *
     * @param initProcessEnvironment the initialise process environment
     * @return the process
     */
    public ProcessInformation setInitProcessEnvironment(IProcessEnvironment initProcessEnvironment) {
        this.initProcessEnvironment = initProcessEnvironment;
        return this;
    }


    /**
     * @see com.github.toolarium.process.runner.dto.process.IProcessInformation#getProcessEnvironment()
     */
    @Override
    public IProcessEnvironment getProcessEnvironment() {
        return processEnvironment;
    }
    

    /**
     * Set the process environment
     *
     * @param processEnvironment the process environment
     * @return the process
     */
    public ProcessInformation setProcessEnvironment(IProcessEnvironment processEnvironment) {
        this.processEnvironment = processEnvironment;
        return this;
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(basicAuth, healthPath, initProcessEnvironment, name, port, processEnvironment, resourcePath);
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
        
        ProcessInformation other = (ProcessInformation) obj;
        return Objects.equals(basicAuth, other.basicAuth) && Objects.equals(healthPath, other.healthPath)
                && Objects.equals(initProcessEnvironment, other.initProcessEnvironment)
                && Objects.equals(name, other.name) && port == other.port
                && Objects.equals(processEnvironment, other.processEnvironment)
                && Objects.equals(resourcePath, other.resourcePath);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProcessInformation [name=" + name + ", port=" + port + ", resourcePath=" + resourcePath + ", healthPath=" + healthPath 
                + ", basicAuth=" + basicAuth 
                + ", initProcessEnvironment=" + initProcessEnvironment
                + ", processEnvironment=" + processEnvironment + "]";
    }
}

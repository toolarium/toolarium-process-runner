/*
 * ProcessEnvironment.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.dto.environment;

import com.github.toolarium.process.runner.dto.base.IProcessProperties;
import com.github.toolarium.process.runner.dto.base.ISecuredValue;
import com.github.toolarium.process.runner.dto.base.ProcessProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Implements the {@link IProcessEnvironment}.
 * 
 * @author patrick
 */
public class ProcessEnvironment implements IProcessEnvironment, Serializable {
    private static final long serialVersionUID = 6866536291715372817L;
    private IProcessProperties systemProperties;
    private IProcessProperties environmentProperties;
    private List<ISecuredValue<String>> processParameters;
    private String command;
    private String user;
    private String workingPath;
    private String tempPath;
    private String os;
    private String osVersion;
    private String osArchitecture;

    
    /**
     * Constructor for ProcessEnvironment
     */
    public ProcessEnvironment() {
        systemProperties = new ProcessProperties();
        environmentProperties = new ProcessProperties();
        processParameters = new ArrayList<>();
        command = "";
        user = null;
        workingPath = null;
        tempPath = null;
        os = null;
        osVersion = null;
        osArchitecture = null;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getSystemProperties()
     */
    @Override
    public IProcessProperties getSystemProperties() {
        return systemProperties;
    }

    
    /**
     * Set the system properties
     *
     * @param systemProperties the system properties
     * @return the process environment
     */
    public ProcessEnvironment setSystemProperties(IProcessProperties systemProperties) {
        this.systemProperties = systemProperties;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getEnvironmentProperties()
     */
    @Override
    public IProcessProperties getEnvironmentProperties() {
        return environmentProperties;
    }

    
    /**
     * Set the environment properties
     *
     * @param environmentProperties the environment properties
     * @return the process environment
     */
    public ProcessEnvironment setEnvironmentProperties(IProcessProperties environmentProperties) {
        this.environmentProperties = environmentProperties;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getProcessParameters()
     */
    @Override
    public List<ISecuredValue<String>> getProcessParameters() {
        return processParameters;
    }

    
    /**
     * Set the process parameters
     *
     * @param processParameters the process parameters
     * @return the process environment
     */
    public ProcessEnvironment setProcessParameters(List<ISecuredValue<String>> processParameters) {
        this.processParameters = processParameters;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getCommand()
     */
    @Override
    public String getCommand() {
        return command;
    }

    
    /**
     * Set the command
     *
     * @param command the command
     * @return the process environment
     */
    public ProcessEnvironment setCommand(String command) {
        this.command = command;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getUser()
     */
    @Override
    public String getUser() {
        return user;
    }

    
    /**
     * Set the user
     *
     * @param user the user
     * @return the process environment
     */
    public ProcessEnvironment setUser(String user) {
        this.user = user;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getWorkingPath()
     */
    @Override
    public String getWorkingPath() {
        return workingPath;
    }

    
    /**
     * Set the working path
     *
     * @param workingPath the working path
     * @return the process environment
     */
    public ProcessEnvironment setWorkingPath(String workingPath) {
        this.workingPath = workingPath;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getTempPath()
     */
    @Override
    public String getTempPath() {
        return tempPath;
    }

    
    /**
     * Set the temp path
     *
     * @param tempPath the temp path
     * @return the process environment
     */
    public ProcessEnvironment setTempPath(String tempPath) {
        this.tempPath = tempPath;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getOS()
     */
    @Override
    public String getOS() {
        return os;
    }

    
    /**
     * Set the os
     *
     * @param os the os
     * @return the process environment
     */
    public ProcessEnvironment setOS(String os) {
        this.os = os;
        return this;
    }

        
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getOSVersion()
     */
    @Override
    public String getOSVersion() {
        return osVersion;
    }

    
    /**
     * Set the os version
     *
     * @param osVersion the os version
     * @return the process environment
     */
    public ProcessEnvironment setOSVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    
    /**
     * @see com.github.toolarium.process.runner.dto.environment.IProcessEnvironment#getArchitecture()
     */
    @Override
    public String getArchitecture() {
        return osArchitecture;
    }

    
    /**
     * Set the os architecture
     *
     * @param osArchitecture the os architecture
     * @return the process environment
     */
    public ProcessEnvironment setArchitecture(String osArchitecture) {
        this.osArchitecture = osArchitecture;
        return this;
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(environmentProperties, os, osArchitecture, osVersion, processParameters, systemProperties, tempPath, user, workingPath, command);
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
        
        ProcessEnvironment other = (ProcessEnvironment) obj;
        return Objects.equals(environmentProperties, other.environmentProperties) && Objects.equals(os, other.os)
                && Objects.equals(osArchitecture, other.osArchitecture) && Objects.equals(osVersion, other.osVersion)
                && Objects.equals(processParameters, other.processParameters)
                && Objects.equals(systemProperties, other.systemProperties) && Objects.equals(tempPath, other.tempPath)
                && Objects.equals(user, other.user) && Objects.equals(workingPath, other.workingPath)
                && Objects.equals(command, other.command);
    }


    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ProcessEnvironment [" 
                + "workingPath=" + workingPath + ", tempPath=" + tempPath 
                + ", environmentProperties=" + environmentProperties + ", systemProperties=" + systemProperties
                + ", command=" + command + ", processParameters=" + processParameters 
                + ", user=" + user + ", os=" + os + ", osVersion=" + osVersion + ", osArchitecture=" + osArchitecture + "]";
    }
}

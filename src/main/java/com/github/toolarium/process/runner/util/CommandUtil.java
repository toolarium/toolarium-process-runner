/*
 * CommandUtil.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.util;

import com.github.toolarium.process.runner.dto.base.IProcessProperties;
import com.github.toolarium.process.runner.dto.base.ISecuredValue;
import com.github.toolarium.process.runner.dto.environment.IProcessEnvironment;
import com.github.toolarium.system.command.dto.SystemCommand;
import java.util.List;


/**
 * Command utility
 *  
 * @author patrick
 */
public final class CommandUtil {
    /**
     * Private class, the only instance of the singelton which will be created by accessing the holder class.
     *
     * @author patrick
     */
    private static class HOLDER {
        static final CommandUtil INSTANCE = new CommandUtil();
    }

    
    /**
     * Constructor
     */
    private CommandUtil() {
        // NOP
    }

    
    /**
     * Get the instance
     *
     * @return the instance
     */
    public static CommandUtil getInstance() {
        return HOLDER.INSTANCE;
    }

    
    /**
     * Prepare a process environment
     * 
     * @param inputProcessEnvironment the process environment
     * @return the prepared process environment
     */
    public com.github.toolarium.system.command.dto.env.IProcessEnvironment prepareProcessEnvironment(IProcessEnvironment inputProcessEnvironment) {
        com.github.toolarium.system.command.dto.env.ProcessEnvironment processEnvironment = new com.github.toolarium.system.command.dto.env.ProcessEnvironment();
        
        IProcessProperties environmentProperties = inputProcessEnvironment.getEnvironmentProperties();
        if (environmentProperties != null && !environmentProperties.isEmpty()) {
            for (String key : environmentProperties.keySet()) {
                if (key != null && !key.isBlank()) {
                    processEnvironment.setEnvironmentVariable(key.trim(), environmentProperties.getProprty(key).trim());
                }
            }
        }

        if (inputProcessEnvironment.getWorkingPath() != null) {
            processEnvironment.setWorkingPath(inputProcessEnvironment.getWorkingPath());
        }
        

        processEnvironment.setUser(inputProcessEnvironment.getUser());

        /* TODO: set user, os, osVersion and arhcitecture :-)  
        String os = inputProcessEnvironment.getOS();
        String osVersion = inputProcessEnvironment.getOSVersion();
        String architecture = inputProcessEnvironment.getArchitecture();
        processEnvironment.getArchitecture();
        processEnvironment.getOS();
        processEnvironment.getOSVersion();
        processEnvironment.setUser(inputProcessEnvironment.getUser());
        */

        return processEnvironment;
    }

    
    /**
     * Get the environment set command
     *
     * @return the environment set command
     */
    public String getEnvironmentSetCommand() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("windows")) {
            return "SET";
        }
        return "export";
    }

    
    /**
     * Get the environment assign command
     *
     * @return the environment assign command
     */
    public String getEnvironmentAssignCommand() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("windows")) {
            return "=";
        }
        return "=";
    }

    
    /**
     * Prepare a process environment
     * 
     * @param inputProcessEnvironment the process environment
     * @return the prepared process environment
     */
    public String prepareProcessCommand(IProcessEnvironment inputProcessEnvironment) {
        StringBuilder setCommand = new StringBuilder();
        IProcessProperties environmentProperties = inputProcessEnvironment.getEnvironmentProperties();
        if (environmentProperties != null && !environmentProperties.isEmpty()) {
            for (String key : environmentProperties.keySet()) {
                if (key != null && !key.isBlank()) {

                    setCommand.append(getEnvironmentSetCommand());
                    setCommand.append(" ");
                    setCommand.append(key.trim());
                    setCommand.append(getEnvironmentAssignCommand());
                    
                    if (environmentProperties.getProprty(key) != null && !environmentProperties.getProprty(key).isBlank()) {
                        setCommand.append(environmentProperties.getProprty(key).trim());
                    }
                    setCommand.append("\r");
                    setCommand.append("\n");
                }
            }
        }

        return setCommand.toString();
    }

    
    /**
     * Prepare a process environment
     * 
     * @param inputProcessEnvironment the process environment
     * @return the prepared process environment
     */
    public com.github.toolarium.system.command.dto.ISystemCommand prepareSystemCommand(IProcessEnvironment inputProcessEnvironment) {
        StringBuilder parameters = new StringBuilder();
        StringBuilder logParameters = new StringBuilder();

        // add command
        if (inputProcessEnvironment.getCommand() != null) {
            parameters.append(inputProcessEnvironment.getCommand());
            logParameters.append(inputProcessEnvironment.getCommand());
        }

        // prepare system properties
        IProcessProperties systemProperties = inputProcessEnvironment.getSystemProperties();
        if (systemProperties != null && !systemProperties.isEmpty()) {
            for (String key  : systemProperties.keySet()) {
                if (key != null && !key.isBlank()) {
                    if (!parameters.toString().isEmpty()) {
                        parameters.append(" ");
                        logParameters.append(" ");
                    }

                    parameters.append("-D");
                    parameters.append(key);
                    logParameters.append("-D");
                    logParameters.append(key);
                    
                    String value = systemProperties.getProprty(key, false);
                    if (value != null) {
                        parameters.append("=");
                        parameters.append(value);
                        logParameters.append("=");
                        logParameters.append(systemProperties.getProprty(key, true));
                    }
                }
            }
        }    

        // prepare parameters
        List<ISecuredValue<String>> processParameters = inputProcessEnvironment.getProcessParameters();
        for (ISecuredValue<String> parameter : processParameters) {
            if (!parameters.toString().isEmpty()) {
                parameters.append(" ");
                logParameters.append(" ");
            }
            
            parameters.append(parameter.getValue());
        }
        
        SystemCommand systemCommand = new SystemCommand();
        systemCommand.add(parameters.toString(), logParameters.toString());
        return systemCommand;
    }
}

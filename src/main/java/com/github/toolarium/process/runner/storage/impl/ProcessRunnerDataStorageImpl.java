/*
 * ProcessRunnerDataStorageImpl.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.storage.impl;

import com.github.toolarium.process.runner.dto.IProcessRuntimeEnvironment;
import com.github.toolarium.process.runner.dto.ProcessRuntimeEnvironment;
import com.github.toolarium.process.runner.dto.action.IProcessRunnerAction;
import com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.IProcessInformation;
import com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implements the IProcessRunnerDataStorage.
 * 
 * @author patrick
 */
public class ProcessRunnerDataStorageImpl implements IProcessRunnerDataStorage {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessRunnerDataStorageImpl.class);
    private transient volatile Map<UUID, IProcessRuntimeEnvironment> processRuntimeEnvironmentMap;
    private transient volatile ConcurrentLinkedQueue<IProcessRunnerAction> processActionQueue;
    
    /**
     * Constructor for ProcessRunnerDataStorageImpl
     */
    public ProcessRunnerDataStorageImpl() {
        processRuntimeEnvironmentMap = new ConcurrentHashMap<>();
        processActionQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * @see com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage#add(com.github.toolarium.process.runner.dto.process.IProcessInformation, com.github.toolarium.process.runner.dto.monitor.IProcessMonitorInformation)
     */
    @Override
    public IProcessRuntimeEnvironment add(IProcessInformation processInformation, IProcessMonitorInformation processMonitorInformation) {
        // create new process
        UUID uuid = UUID.randomUUID();
        IProcessRuntimeEnvironment processRuntimeEnvironment = new ProcessRuntimeEnvironment(uuid, processInformation, processMonitorInformation);
        processRuntimeEnvironmentMap.put(processRuntimeEnvironment.getUUID(), processRuntimeEnvironment);
        LOG.debug("Registered new process runtime environment [" + processInformation.getName() + "] on port [" + processInformation.getPort() + "] with resource [" + processInformation.getResourcePath() + "] -> as [" + uuid + "]");
        return processRuntimeEnvironment;
    }


    /**
     * @see com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage#remove(java.util.UUID)
     */
    @Override
    public IProcessRuntimeEnvironment remove(UUID uuid) {
        IProcessRuntimeEnvironment processRuntimeEnvironment = processRuntimeEnvironmentMap.remove(uuid);
        if (processRuntimeEnvironment != null) {
            IProcessInformation processInformation = processRuntimeEnvironment.getProcessInformation();
            LOG.debug("Unregistered process runtime environment [" + processInformation.getName() + "] on port [" + processInformation.getPort() + "] with resource [" + processInformation.getResourcePath() + "] -> as [" + uuid + "]");
        }

        return processRuntimeEnvironment;
    }


    /**
     * @see com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage#getQueue()
     */
    @Override
    public Queue<IProcessRunnerAction> getQueue() {
        return processActionQueue;
    }


    /**
     * @see com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage#getProcessList()
     */
    @Override
    public Set<UUID> getProcessList() {
        return processRuntimeEnvironmentMap.keySet();
    }


    /**
     * @see com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage#getProcessInformation(java.util.UUID)
     */
    @Override
    public IProcessInformation getProcessInformation(UUID uuid) {
        IProcessRuntimeEnvironment processRuntimeEnvironment = processRuntimeEnvironmentMap.get(uuid);
        if (processRuntimeEnvironment != null) {
            return processRuntimeEnvironment.getProcessInformation();
        }
        return null;
    }


    /**
     * @see com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage#getProcessMonitorInformation(java.util.UUID)
     */
    @Override
    public IProcessMonitorInformation getProcessMonitorInformation(UUID uuid) {
        IProcessRuntimeEnvironment processRuntimeEnvironment = processRuntimeEnvironmentMap.get(uuid);
        if (processRuntimeEnvironment != null) {
            return processRuntimeEnvironment.getProcessMonitorInformation();
        }
        return null;
    }
}

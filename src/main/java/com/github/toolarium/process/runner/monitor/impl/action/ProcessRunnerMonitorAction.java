/*
 * MonitorRunner.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.monitor.impl.action;

import com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Defines the monitor process runner action
 * 
 * @author patrick
 */
public class ProcessRunnerMonitorAction extends AbstractProcessRunnerAction {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessRunnerMonitorAction.class);
    
    
    /**
     * Constructor for ProcessRunnerMonitorAction
     *
     * @param processRunnerDataStorage the process runner data storage
     */
    public ProcessRunnerMonitorAction(IProcessRunnerDataStorage processRunnerDataStorage) {
        super(processRunnerDataStorage);
    }

    
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            LOG.debug("Process monitor service...");
            Set<UUID> uuids = getProcessRunnerDataStorage().getProcessList();
            if (uuids != null && !uuids.isEmpty()) {
                for (UUID uuid : uuids) {
                    LOG.debug("Verify process [" + uuid + "]...");
                    // TODO:
                }
            }
        } catch (RuntimeException e) {
            LOG.info("Interupt monitor service.");
            Thread.currentThread().interrupt();
        }
    }
}

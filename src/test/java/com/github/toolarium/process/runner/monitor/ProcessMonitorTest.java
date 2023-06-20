/*
 * ProcessMonitorTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.toolarium.process.runner.dto.monitor.ProcessMonitorInformation;
import com.github.toolarium.process.runner.dto.process.ProcessInformation;
import com.github.toolarium.process.runner.storage.IProcessRunnerDataStorage;
import com.github.toolarium.process.runner.storage.ProcessRunnerDataStorageFactory;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test the {@link ProcessMonitorService}
 * 
 * @author patrick
 */
public class ProcessMonitorTest {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessMonitorTest.class);

    /**
     * Test the start and stop
     */
    @Test void startTest() {
        ProcessMonitorService processMonitorService = ProcessMonitorServiceFactory.getInstance().getProcessMonitorService();
        
        IProcessRunnerDataStorage processRunnerData = ProcessRunnerDataStorageFactory.getInstance().getProcessRunnerData();
        processMonitorService.init(processRunnerData);
        
        processMonitorService.setMonitorTimeSetting(0, 200, TimeUnit.MILLISECONDS);
        assertNotNull(processRunnerData.getProcessList());
        assertEquals(0, processRunnerData.getProcessList().size());
        
        UUID uuid = processMonitorService.registerProcess(new ProcessInformation(), new ProcessMonitorInformation());
        assertNotNull(uuid);
        assertEquals(1, processRunnerData.getProcessList().size());
        
        sleep(400);
        LOG.info("STOP");
        processMonitorService.stopProcessMonitor();
        sleep(400);
        LOG.info("START");
        processMonitorService.startProcessMonitor();
        assertEquals(1, processRunnerData.getProcessList().size());
        sleep(400);
        processMonitorService.stopProcessMonitor();
        LOG.info("STOP");
        sleep(400);
    }


    /**
     * Sleep
     * 
     * @param value the value
     */
    private void sleep(long value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            // NOP
        }
    }
}

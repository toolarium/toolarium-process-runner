/*
 * FileAccessLogReceiverTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.logger.access;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import jptools.resource.FileAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test the {@link FileAccessLogReceiver}.
 * 
 * @author patrick
 */
public class FileAccessLogReceiverTest {
    private static final String TEMP_PATH = "build/logtest/";
    private static final String FILENAME_EXTENSION = ".log";
    private static final String FILE_BASENAME = "t";
    private static final String FILENAME = FILE_BASENAME + FILENAME_EXTENSION;

    
    
    /**
     * Delete log folder
     */
    @BeforeEach void deleteLogs() {
        FileAccess.getInstance().removeDirectory(new File(TEMP_PATH));
    }

    
    /**
     * Test MyApplication method.
     */
    @Test void testFilename() {
        FileAccessLogReceiver receiver = new FileAccessLogReceiver(TEMP_PATH + FILENAME);
        assertEquals(TEMP_PATH + FILENAME, receiver.getCurrentFileName());
        assertEquals(TEMP_PATH + FILE_BASENAME + "-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + FILENAME_EXTENSION, receiver.getFileName());
        receiver.close();
    }

    
    /**
     * Test MyApplication method.
     * 
     * @throws InterruptedException In case of interrupt 
     */
    @Test void createContent() throws InterruptedException {
        FileAccessLogReceiver receiver = new FileAccessLogReceiver(TEMP_PATH + FILENAME);
        receiver.logMessage("1");
        receiver.logMessage("2");

        String filename = receiver.getFileName();
        assertEquals(4,  new File(receiver.getCurrentFileName()).length());
        assertEquals(0,  new File(filename).length());
        receiver.close();        
        assertEquals(0,  new File(receiver.getCurrentFileName()).length());
        assertEquals(4,  new File(filename).length());
        
        receiver = new FileAccessLogReceiver(TEMP_PATH + FILENAME);
        receiver.logMessage("3");
        receiver.logMessage("4");        
        assertEquals(4,  new File(receiver.getCurrentFileName()).length());
        assertEquals(4,  new File(filename).length());

        receiver.close();        
        assertEquals(0,  new File(receiver.getCurrentFileName()).length());
        assertEquals(8,  new File(filename).length());
    }
}

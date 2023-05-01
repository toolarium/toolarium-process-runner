/*
 * ProcessRunnerTest.java
 *
 * Copyright by toolarium, all rights reserved.
 */

package com.github.toolarium.process.runner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * MyApplicationTest.
 *
 * <p>! This is just a sample please remove it. !</p>
 */
public class ProcessRunnerTest {
    /**
     * Test MyApplication method.
     */
    @Test void testSomeLibraryMethod() {
        ProcessRunner.main(new String[] {});
        //ProcessRunner classUnderTest = new ProcessRunner();
        //assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'");
    }
    
    
    /**
     * Test Version.
     */
    @Test void testVersion() {
        assertEquals(Version.VERSION, Version.getVersion());
        new Version();
        Version.main(new String[]{});
    }
}

/*
 * PathResourceManager.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.network.handler.resource;

import com.github.toolarium.process.runner.config.IProcessRunnerConfiguration;
import io.undertow.server.handlers.resource.Resource;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Path resource manager
 *
 * @author patrick
 */
public class PathResourceManager extends io.undertow.server.handlers.resource.PathResourceManager {
    private static final Logger LOG = LoggerFactory.getLogger(PathResourceManager.class);
    private final IProcessRunnerConfiguration configuration;


    /**
     * Constructor for PathResourceManager
     *
     * @param configuration the configuration
     * @param base the base
     * @param transferMinSize the transfer min size
     */
    public PathResourceManager(final IProcessRunnerConfiguration configuration, final Path base, long transferMinSize) {
        super(base, transferMinSize);
        this.configuration = configuration;
    }


    /**
     * @see io.undertow.server.handlers.resource.PathResourceManager#getResource(java.lang.String)
     */
    @Override
    public Resource getResource(String path)  {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Get resource for path [" + path + "].");
        }
        
        Resource resource = super.getResource(path);
        configuration.getDirectory();
        return resource;
    }
}

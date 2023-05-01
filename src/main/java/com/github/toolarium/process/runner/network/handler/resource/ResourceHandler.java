/*
 * ResourceHandler.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.network.handler.resource;

import com.github.toolarium.process.runner.config.IProcessRunnerConfiguration;
import com.github.toolarium.process.runner.network.handler.auth.BasicAuthenticationHttpHandler;
import io.undertow.Handlers;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.RedirectHandler;
import io.undertow.util.Methods;
import java.nio.file.Paths;


/**
* Basic authentication utility
*
* @author patrick
*/
public final class ResourceHandler {
    /** SLASH */
    public static final String SLASH = "/";


    /**
     * Constructor for ResourceHandler
     */
    private ResourceHandler() {
        // NOP
    }


    /**
     * Add basic authentication
     *
     * @param configuration the configuration
     * @param routinrgHandler the routing handler
     * @return the handler
     */
    public static RoutingHandler addHandler(final IProcessRunnerConfiguration configuration, final RoutingHandler routinrgHandler) {
        String path = configuration.getDirectory();
        io.undertow.server.handlers.resource.ResourceHandler resourceHandler;

        String resourcePath = configuration.getResourcePath();
        if (resourcePath == null || resourcePath.isBlank()) {
            resourcePath = SLASH;
        }

        resourceHandler = Handlers.resource(new PathResourceManager(configuration, Paths.get(path), 10));

        routinrgHandler.add(Methods.GET, resourcePath + "*", BasicAuthenticationHttpHandler.addHandler(configuration, resourceHandler));
        routinrgHandler.setFallbackHandler(new RedirectHandler(resourcePath));
        return routinrgHandler;
    }
}

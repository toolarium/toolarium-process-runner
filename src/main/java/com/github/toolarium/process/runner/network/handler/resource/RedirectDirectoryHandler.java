/*
 * RedirectDirHandler.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.network.handler.resource;

import com.github.toolarium.process.runner.config.IProcessRunnerConfiguration;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.Methods;
import io.undertow.util.RedirectBuilder;
import io.undertow.util.StatusCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Redirect directory handler
 *
 * @author patrick
 */
public class RedirectDirectoryHandler implements HttpHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RedirectDirectoryHandler.class);
    private static final String HTTP2_UPGRADE_PREFIX = "h2";
    private final IProcessRunnerConfiguration configuration;
    private final HttpHandler next;


    /**
     * Constructor for RedirectDirHandler
     *
     * @param configuration the configuration
     * @param next the next handler
     */
    public RedirectDirectoryHandler(final IProcessRunnerConfiguration configuration, HttpHandler next) {
        this.configuration = configuration;
        this.next = next;
    }


    /**
     * @see io.undertow.server.HttpHandler#handleRequest(io.undertow.server.HttpServerExchange)
     */
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        String resourcePath = configuration.getResourcePath();
        if (!resourcePath.endsWith(ResourceHandler.SLASH)) {
            resourcePath += ResourceHandler.SLASH;
        }
        final boolean redirect = !exchange.getRelativePath().startsWith(resourcePath);

        // https://issues.jboss.org/browse/WFLY-3439: if the request is an upgrade request then we don't want to redirect
        // as there is a good chance the web socket client won't understand the redirect we make an exception for HTTP2
        // upgrade requests, as this would have already be handled at the connector level if it was going to be handled.
        final String upgradeString = exchange.getRequestHeaders().getFirst(Headers.UPGRADE);
        final boolean isUpgradeRequest = upgradeString != null && !upgradeString.startsWith(HTTP2_UPGRADE_PREFIX);

        if (redirect && !isUpgradeRequest) {
            // UNDERTOW-89: we redirect on GET requests to the root context to add an / to the end
            if (exchange.getRequestMethod().equals(Methods.GET) || exchange.getRequestMethod().equals(Methods.HEAD)) {
                exchange.setStatusCode(StatusCodes.FOUND);
            } else {
                exchange.setStatusCode(StatusCodes.TEMPORARY_REDIRECT);
            }

            if (!exchange.getRelativePath().endsWith(ResourceHandler.SLASH)) {
                resourcePath = exchange.getRelativePath() + ResourceHandler.SLASH;
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Directory redirect: " + exchange.getRelativePath() + " -> " + resourcePath);
                }

                exchange.getResponseHeaders().put(Headers.LOCATION, RedirectBuilder.redirect(exchange, resourcePath, true));
                return;
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Redirect to sub url: " + exchange.getRelativePath() + " -> " + resourcePath);
                }
            }
        }

        next.handleRequest(exchange);
    }
}

/*
 * AccessLogHandlerUtil.java
 *
 * Copyright by toolarium, all rights reserved.
 */
package com.github.toolarium.process.runner.logger.access;

import com.github.toolarium.process.runner.config.IProcessRunnerConfiguration;
import com.github.toolarium.process.runner.logger.VerboseLevel;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.accesslog.AccessLogHandler;
import io.undertow.server.handlers.accesslog.AccessLogReceiver;

/**
 * The access log handler utility
 *  
 * @author patrick
 */
public final class AccessLogHttpHandler {
    
    /**
     * Constructor for AccessLogHttpHandler
     */
    private AccessLogHttpHandler() {
        // NOP
    }

    
    /**
     * Add basic authentication
     *
     * @param configuration the configuration
     * @param handlerToWrap the handler to wrap
     * @return the handler
     */
    public static HttpHandler addHandler(final IProcessRunnerConfiguration configuration, final HttpHandler handlerToWrap) {
        
        if (VerboseLevel.VERBOSE.equals(configuration.getVerboseLevel()) || VerboseLevel.ACCESS.equals(configuration.getVerboseLevel())) {
            final AccessLogReceiver accessLogReceiver = new FileAccessLogReceiver("");
            return new AccessLogHandler(handlerToWrap, accessLogReceiver, configuration.getAccessLogFormatString(), AccessLogHttpHandler.class.getClassLoader());
        } else if (VerboseLevel.ACCESS_CONSOLE.equals(configuration.getVerboseLevel())) {
            //LogbackUtil.getInstance().detachAppender(ACCESSLOG_APPENDER_NAME);
            final AccessLogReceiver accessLogReceiver = new StdoutAccessLogReceiver();
            return new AccessLogHandler(handlerToWrap, accessLogReceiver, configuration.getAccessLogFormatString(), AccessLogHttpHandler.class.getClassLoader());
        } else {
            //LogbackUtil.getInstance().detachAppender(ACCESSLOG_APPENDER_NAME);
        }
        
        return handlerToWrap;
    }
}

package com.example.demo.common.util;

import java.util.logging.Logger;

/**
 * The common logger utility.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public final class Log {
    /**
     * The inform logger.
     */
    public static final Logger inform = Logger.getLogger("Informer");
    /**
     * The tracking logger.
     */
    public static final Logger track = Logger.getLogger("Tracker");

    private Log(){
        throw new AssertionError();
    }
}

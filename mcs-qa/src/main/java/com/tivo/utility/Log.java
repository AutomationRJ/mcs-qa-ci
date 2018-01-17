package com.tivo.utility;

import org.apache.log4j.Logger;

/**
 * Created by rahul.jaiswal on 14/10/16.
 */
public class Log {

    private static Logger Log = Logger.getLogger(com.tivo.utility.Log.class.getName());

    public static void info(String message) {

        Log.info(message);

    }

    public static void warn(String message) {

        Log.warn(message);

    }

    public static void error(String message) {

        Log.error(message);

    }

    public static void fatal(String message) {

        Log.fatal(message);

    }

    public static void debug(String message) {

        Log.debug(message);

    }
}

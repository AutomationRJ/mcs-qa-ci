package com.tivo.utility;

import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by rjaiswal on 5/5/2017.
 */
public class Util {

    private static final String ZULU_PATTERN = "yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSS'Z'";

    public static Map loadProperties() throws Exception{
        Map<String, String> map = new HashMap<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            FileInputStream fis = null;
            if(System.getProperty("Environment").equalsIgnoreCase("Dev")){
                fis = new FileInputStream(classLoader.getResource("Dev.properties").getFile());
            }else if(System.getProperty("Environment").equalsIgnoreCase("Prod")){
                fis = new FileInputStream(classLoader.getResource("Prod.properties").getFile());
            }else{
                fis = new FileInputStream(classLoader.getResource("Prod.properties").getFile());
            }
            Properties prop = new Properties();
            prop.load(fis);
            Enumeration keys = prop.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                map.put(key, prop.getProperty(key));
            }
        } catch (IOException e){
            Log.error("Class Utils | Method LoadProperties | Exception desc : " + e.getMessage());
        }
        return map;
    }

    public static Long randomNumber(){
        long range = 900000000000L;
        Random rnd = new Random();
        long n = (long)(rnd.nextDouble()*range);
        return n;
    }

    public static String currentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.toString();
    }

    public static long from(){
        Long from = DateTimeFormat
                .forPattern(ZULU_PATTERN)
                .withZone(DateTimeZone.UTC)
                .parseDateTime(currentDate() + "T00:00:00.000Z")
                .getMillis();
        return from;
    }

    public static long to(){
        Long to = DateTimeFormat
                .forPattern(ZULU_PATTERN)
                .withZone(DateTimeZone.UTC)
                .parseDateTime(currentDate() + "T23:59:59.999Z")
                .getMillis();
        return to;
    }
}

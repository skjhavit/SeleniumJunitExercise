package com.exercise.util;

import java.io.InputStream;
import java.util.Properties;

public class PropManager {
    private static Properties props;

    public static Properties getProps() {
        if (props == null || props.isEmpty()) {
            System.out.println("loading prop file");
            props = new Properties();
            try (InputStream input = PropManager.class.getClassLoader().getResourceAsStream("config.properties")) {
                props.load(input);
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace());
                System.out.println("couldn't load property file, exiting......");
                System.exit(1);
            }
        }
        return props;
    }

}

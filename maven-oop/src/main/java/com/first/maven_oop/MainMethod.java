package com.first.maven_oop;

import org.apache.log4j.Logger;

public class MainMethod {

    private static final Logger LOG = Logger.getLogger(MainMethod.class);

    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.start();
        } catch (Exception e) {
            LOG.error("Error happen", e);
            e.printStackTrace();
        }
    }
}

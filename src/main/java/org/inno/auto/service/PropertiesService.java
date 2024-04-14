package org.inno.auto.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesService {
    private static final Properties properties = new Properties();

    static {
        try (InputStream in = ClassLoader.getSystemResourceAsStream("application.properties")) {
            properties.load(in);
        } catch (Exception e) {
            System.err.println("Ошибка загрузки application.properties" + e.getMessage());
        }
    }

    public static String getProp(String prop) {
        return properties.getProperty(prop);
    }
}

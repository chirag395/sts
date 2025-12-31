
package com.example.empmgmt.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Properties props = new Properties();
    private static boolean loaded = false;

    private static void loadProps() {
        if (loaded) return;
        try (InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found in classpath");
            }
            props.load(in);
            loaded = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getDaoType() {
        loadProps();
        return props.getProperty("dao.type", "memory");
    }

    public static Connection getConnection() throws SQLException {
        loadProps();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String pass = props.getProperty("db.password");
        if (url == null || user == null) {
            throw new IllegalStateException("Database URL/username are not set in config.properties");
        }
        return DriverManager.getConnection(url,user, pass);
    }
}
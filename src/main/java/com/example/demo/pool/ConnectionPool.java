package com.example.demo.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> free = new LinkedBlockingQueue<>(8);
    private BlockingQueue<Connection> used = new LinkedBlockingQueue<>(8);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public ConnectionPool() {
        //property file
        String url = "jdbc:mysql://localhost:3306/demo_schema";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "mySqlPass89_");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliantTimezoneShift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath:server.crt");

        for (int i = 0; i < 8; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            free.add(connection);
        }
    }

    public static ConnectionPool getInstance() {
        //lock
        instance = new ConnectionPool();
        //unlock
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection){
        try {
            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //deregisterDriver
    public void close() throws SQLException {
        DriverManager.deregisterDriver(new com.mysql.cj.jdbc.Driver());
    }
    public void destroyPool(){
        for (int i = 0; i < 8; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
                //Log e.printStackTrace();
            }
        }
    }
}

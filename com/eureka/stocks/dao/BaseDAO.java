package com.eureka.stocks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class creates a connection to the database when instantiated
 */
public class BaseDAO implements AutoCloseable{
    private static final String JDBC_URL = "jdbc:postgresql://endeavourtech.ddns.net:50271/StocksDB";
    private static final String USERNAME = "evr_sql_app";
    private static final String PASSWORD = "5LViU5pLkSjRHECec9NF4wRxxV";

    protected static Connection connection;

    public BaseDAO() {
        try {
            System.out.println("Before creating a Database connection");
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This overriden method coming from AutoCloseable interface is needed
     * to use try-with-resoruces construct in the Main class to close db connections
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        System.out.println("Before closing the db connection");
        this.connection.close();
    }
}

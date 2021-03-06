package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;

public class DaoFactory {
    private String url;
    private String username;
    private String password;
    private static Log logger = null;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	logger.info(e);
        }      
        return new DaoFactory(
        		"jdbc:mysql://localhost:3306/mavenexo1", "root", "1234"); 
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
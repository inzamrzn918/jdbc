package com.company;

import java.sql.*;
public class DB {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/jdbc";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static Connection connection = null;

    public static Connection connect(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}

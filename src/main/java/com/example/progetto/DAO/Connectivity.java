package com.example.progetto.DAO;
import java.sql.*;
public class Connectivity {
    private final String USER;
    private final String PASS;
    private final String DB;
    public Connection conn;
    private static Connectivity SingletonClass=null;

    protected Connectivity(){
        USER = "root";
        PASS = "luca";
        DB = "jdbc:mysql://localhost:3306/viaggi";
        conn = null;
    }

    public void connected() throws SQLException {
        conn = DriverManager.getConnection(DB, USER, PASS);
    }


    public synchronized static Connectivity getSingletonInstance (){
        if(Connectivity.SingletonClass==null) {
            Connectivity.SingletonClass = new Connectivity();
        }
            return SingletonClass;
    }
}
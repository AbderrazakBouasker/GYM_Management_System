package com.example.mini_projet_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bdconnection {

    public static Connection connecter() throws SQLException {

        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojdb","root","");
        return con;
    }


}

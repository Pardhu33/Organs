package com.organ;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Establish a connection to the MariaDB database
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/organ_donation", "root", "hyd12345");

        } catch (Exception e) {
            // Print the exception if it occurs
            e.printStackTrace();
        }

        return con;
    }
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    public static Connection getConnection() {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/faculty_management",
                    "root",
                    ""
            );

            System.out.println("Connected!");

            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:3306/PKDB?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String usrname = "group_remote";
    private static final String pass = "group";

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, usrname, pass);
    }
}
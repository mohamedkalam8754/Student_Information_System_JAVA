
package com.example.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class StudentDb {
    
    static Connection con;
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3307/studentinfo";
    static String uname = "root";
    static String pass = "root";
    
    public static Connection getConnnection() throws Exception{
        if(con == null){
            Class.forName(driver);
            con = DriverManager.getConnection(url,uname,pass);
        }

        return con;
    }

   

    
}

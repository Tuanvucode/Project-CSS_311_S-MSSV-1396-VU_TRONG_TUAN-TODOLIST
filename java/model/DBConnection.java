/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/todo_app";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối database thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database: " + e.getMessage());
        }
        return conn;
    }
}

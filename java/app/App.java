/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package app;

import view.MainFrame;
import model.DBConnection;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
        
//        DBConnection DBCN = new DBConnection();
//        DBCN.getConnection();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tria
 */
public class Koneksi {
    public Statement stat;
    public ResultSet res;
    public PreparedStatement pst;
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException {
        try{
            String url = "jdbc:mysql://localhost:3306/e_payment";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url,user,pass);
//            System.out.println("Koneksi Berhasil");
        }catch(Exception e){
            System.err.println("koneksi gagal"+ e.getMessage());
        }
    return mysqlconfig;
    }
}

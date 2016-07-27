/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbfapp;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Usuario
 */
public class DB {
    
    
    public void connect(String url,
            String userName, String password){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("where is your MYSQL JDBC Driver");
        }
        
        Connection  connection = null;
        
        try{
            connection = DriverManager
                    .getConnection(url, userName, password);
        }
        catch(SQLException e){
            System.err.println("Connection Failed! Check output console");
            System.err.println(e.getErrorCode());
        }
        
        if(connection != null){
            try {
                System.out.println("You made it, take control your database now");
                
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * from bfimport_osc.orders");
                
                while(rs.next()){
                    int numColumns = rs.getMetaData().getColumnCount();
                    for (int i=1; i<=rs.getMetaData().getColumnCount(); i++){
                        System.out.println("column " + i + "=" + rs.getObject(i));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("Failed to make connection!");
        }
        
    }
}

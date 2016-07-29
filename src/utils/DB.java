/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import sbfapp.Configuration;
/**
 *
 * @author Uclides Gil
 */
public class DB implements Runnable{
    Configuration f = new Configuration();
    
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    
    private Connection  connection = null;
    private boolean status_db;
    
    @Override
    public void run(){
                
                f.readFile("C:\\Users\\Usuario\\Documents\\Proyectos\\sbfapp\\src\\sbfapp\\data.json");
                
                connect("", 
                "", "");   
                
                if(isStatus_db() == true){
                //refresh interface
                }
    }
    //connect to DB
    public boolean connect(String url,
            String userName, String password){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("where is your MYSQL JDBC Driver");
        }
        
        try{
            connection = DriverManager
                    .getConnection(url, userName, password);
        }
        catch(SQLException e){
            System.err.println("Connection Failed! Check output console");
            System.err.println(e.getErrorCode());
        }
        
        if(connection != null){
               System.out.println("Success to connect DB");
               setStatus_db(true);
        }
        else{
            System.out.println("Failed to make connection!");
        setStatus_db(false);    
        }
        return status_db;
    }
    
   /**get data of the query of generally form
    * @param query define the string to create the query
    * @return a Resultset with results of the query
    */
    public ResultSet getAllItems(String query){
        ResultSet rs = null;
        
        try {
            System.out.println("You made it, take control your database now");   
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
      return rs;
    }
    
    
    /**get status of DB with iteration of 60 seconds if connection fail
     *@param task define the task for de connection with mysql 
     */
    public void getStatusDB(Runnable task){
    
        task.run();
//        final Runnable actualTask = null;
//
//        executorService.scheduleAtFixedRate(new Runnable() {
//            private final ExecutorService executor = Executors.newSingleThreadExecutor();
//            private Future<?> lastExecution;
//            @Override
//            public void run() {
//                if (lastExecution != null && !lastExecution.isDone()) {
//                    return;
//                }
//                lastExecution = executor.submit(task);
//            }
//        }, 5, 60, TimeUnit.SECONDS);
    }
    
    
    
    /** getters  and setters of the class
     * 
     *  
     */
    public boolean isStatus_db() {
        return status_db;
    }

    public void setStatus_db(boolean status_db) {
        this.status_db = status_db;
    }
    
    
    
}

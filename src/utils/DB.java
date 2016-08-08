/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import tray.animations.AnimationType;
/**
 *
 * @author Uclides Gil
 */
public class DB {
  private static final Logger logger = Logger.getLogger(DB.class.getName());
  private static final String[] SAMPLE_NAME_DATA = { "John", "Jill", "Jack", "Jerry" };
  
  
  // executes database operations concurrent to JavaFX operations.
  private ExecutorService databaseExecutor;
 
  // the future's data will be available once the database setup has been complete.
  private Future          databaseSetupFuture;
 
  Notifications notifications = new Notifications();
  
  // setting the database executor thread pool size to 1 ensures 
  // only one database command is executed at any one time. 
  // shutdown the program.
  public void stop() throws Exception {
    databaseExecutor.shutdown();
    if (!databaseExecutor.awaitTermination(3, TimeUnit.SECONDS)) {
      logger.info("Database execution thread timed out after 3 seconds rather than shutting down cleanly.");
    }
  }
  
  // start connection DB

  public void start() throws InterruptedException, ExecutionException {
    // wait for the database setup to complete cleanly before showing any UI.
    // a real app might use a preloader or show a splash screen if this 
    // was to take a long time rather than just pausing the JavaFX application thread.
//    databaseSetupFuture.get();
//    
//      System.out.println(databaseExecutor.isShutdown());
//    if(databaseExecutor.isShutdown() == false && setup.getStatus() == true  ){
//        notifications.createNotDesktop(new Image("images/success.png"), "Base de Datos",
//                "Se ha establecido su conexión exitosamente", Paint.valueOf("#333"),
//                AnimationType.POPUP, 3);
//        
//        //databaseExecutor.submit(fetchNamesTask);
//    }
//    else{
//        notifications.createNotDesktop(new Image("images/error.png"), "Base de Datos",
//                "Error al intentar conectar", Paint.valueOf("#333"),
//                AnimationType.POPUP, 3);
//    }
  }
 

  
  class FechOrders extends DBTask<ObservableList<String>>{

        @Override
        protected ObservableList<String> call() throws Exception {
           Thread.sleep(1000);
           
           try(Connection con = getConnection()){
               return FechAllOrders(con);
           }
        }

        private ObservableList<String> FechAllOrders(Connection con) throws SQLException {
            logger.info("Fetching names from database");
            ObservableList<String> orders = FXCollections.observableArrayList();

            Statement st = con.createStatement();      
            ResultSet rs = st.executeQuery("select * from orders");
            while (rs.next()) {
              orders.add(rs.getString("id"));
              orders.add(rs.getString("date"));
              orders.add(rs.getString("user_id"));
            }

            logger.log(Level.INFO, "Found {0}", orders.toString());

            return orders;
        }
  
  }
  
  Connection getConnection() throws ClassNotFoundException, SQLException {
    logger.info("Getting a database connection");
    return DriverManager.getConnection("jdbc:mysql://host", "user", "pass");
  }
}
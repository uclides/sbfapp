/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void initParameterDB() throws Exception {
    databaseExecutor = Executors.newFixedThreadPool(
      1, 
      new DatabaseThreadFactory()
    );  
 
    // run the database setup in parallel to the JavaFX application setup.
    DBSetupTask setup = new DBSetupTask();
    databaseSetupFuture = databaseExecutor.submit(setup);
    
  }
 
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
    databaseSetupFuture.get();
        
    notifications.createNotDesktop(new Image("images/bf.png"), "Base de Datos",
            "Se ha establecido su conexión exitosamente", Paint.valueOf("#333"),
            AnimationType.POPUP, 3);
    
  }
 
  abstract class DBTask<T> extends Task<T> {
    DBTask() {
      setOnFailed(t -> logger.log(Level.SEVERE, null, getException()));
    }
  }
  
  class DBSetupTask extends DBTask {
    @Override protected Void call() throws Exception {
      try (Connection con = getConnection()) {
            if (!schemaExists(con)) {
        }
      }
        
      return null;
    }
    
    private boolean schemaExists(Connection con) {
      logger.info("Checking for Schema existence");      
      try {
          Statement st = con.createStatement();      
        st.executeQuery("select * from orders");
        logger.info("Schema orders exists");      
      } catch (SQLException ex) {
        logger.info("Existing DB not found");
        return false;
      }
 
      return true;
    }
 
    private void createSchema(Connection con) throws SQLException {
      logger.info("Creating schema");
      Statement st = con.createStatement();
      String table = "create table employee(id integer, name varchar(64))";
      st.executeUpdate(table);
      logger.info("Created schema");
    }
 
    private void populateDatabase(Connection con) throws SQLException {
      logger.info("Populating database");      
      Statement st = con.createStatement();      
      for (String name: SAMPLE_NAME_DATA) {
        st.executeUpdate("insert into employee values(1,'" + name + "')");
      }
      logger.info("Populated database");
    }
  }
 
  private Connection getConnection() throws ClassNotFoundException, SQLException {
    logger.info("Getting a database connection");
    return DriverManager.getConnection("jdbc:mysql://www.bfimport.net:3306/bfimport_osc", "bfimport_root", "S0luc10n3sBFrootImp0rt");
  }

  static class DatabaseThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {

            Thread thread = new Thread(r, "Database Connection");
            thread.setDaemon(true);

            return thread;
        }  

  }
}
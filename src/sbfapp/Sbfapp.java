/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbfapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sbfapp.model.Order;
import tray.animations.AnimationType;
import utils.DB;
import utils.DBSetupTask;
import utils.FechOrders;
import utils.Notifications;
/**
 *
 * @author Uclides Gil
 */
public class Sbfapp extends Application {
    
    private ExecutorService databaseExecutor;
    private Future          databaseSetupFuture;
    DB conDB; 
    DBSetupTask setup = new DBSetupTask();
    FechOrders fechOrders = new FechOrders();
    Notifications notifications = new Notifications();
    
    @Override public void init() throws Exception {
        databaseExecutor = Executors.newFixedThreadPool(1, 
                new DatabaseThreadFactory());
       
        databaseSetupFuture = databaseExecutor.submit(setup);
  }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        
        Scene scene = new Scene(root);
    
        stage.setScene(scene);
        stage.show();
        
        //check if connect to DB. Load notification
        setup.setOnSucceeded((Event t) -> {
            notifications.showStatusDB(setup.getStatus());            
        });
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    static class DatabaseThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {

            Thread thread = new Thread(r, "Database Connection");
            thread.setDaemon(true);

            return thread;
        }  

  }

    public FechOrders getFechOrders() {
        return fechOrders;
    }

    public void setFechOrders(FechOrders fechOrders) {
        this.fechOrders = fechOrders;
    }
    
    
      
      
}

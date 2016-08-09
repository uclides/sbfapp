/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbfapp;

import java.sql.Connection;
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
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import utils.DataBase;
import utils.FirstCheckDBService;
import view.FXMLDocumentController;
/**
 *
 * @author Uclides Gil
 */
public class Sbfapp extends Application {
    DataBase dataBase = new DataBase();
    Connection connection;
    @Override public void init() throws Exception {
  
    }
    /**
     * check connection the database and update the GUI
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/main.fxml"));
        Parent root = (Parent) loader.load();
        
        FXMLDocumentController controller = loader.getController();
        
        FirstCheckDBService service= new FirstCheckDBService();
        service.setUrl("jdbc:mysql://host");
        service.setUser("user");
        service.setPassword("password");
        
        service.setOnSucceeded((WorkerStateEvent event) -> {
            Connection con = null;
            try {
                con = service.getValue();
                
                if(con.getClientInfo() !=null)
                    controller.validateGui(true);
                else
                    controller.validateGui(false);
            }
            catch(Exception e) {
                controller.validateGui(false);
                //show message, dialog box, whatever
            }
            finally {
                if(con != null) {
                    try{
                        con.close();
                    }
                    catch(SQLException sqe){
                        //yet another message, unable to close connection cleanly.
                    }
                }
            }
        });
        
        service.start();
       
        Scene scene = new Scene(root);
    
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    } 
   
}

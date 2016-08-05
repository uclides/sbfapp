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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DB;
import sbfapp.model.Order;

/**
 *
 * @author Uclides Gil
 */
public class Sbfapp extends Application {
    
    DB conDB = new DB();
    
    
    @Override public void init() throws Exception {
        conDB.initParameterDB();
  }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        
        Scene scene = new Scene(root);
    
        stage.setScene(scene);
        stage.show();
        
        //test your connection DB
        conDB.start();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
}

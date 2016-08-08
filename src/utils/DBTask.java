/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author Uclides Gil
 */
abstract class DBTask<T> extends Task<T> {
    
    private static final Logger logger = Logger.getLogger(DBTask.class.getName());
    
    DBTask() {
      setOnFailed(t -> logger.log(Level.SEVERE, null, getException()));
    }
  }

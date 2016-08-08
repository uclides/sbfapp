/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uclides Gil
 */
  
  public class DBSetupTask extends DBTask {
     
    private static final Logger logger = Logger.getLogger(DBSetupTask.class.getName()); 
    DB conDB = new DB();
    boolean status = false;
      
    @Override 
    protected Void call() {
      try (Connection con = conDB.getConnection()) {
            if (con.getClientInfo()!= null) {
                setStatus(true);
        }

      }
      catch(SQLException e){
        setStatus(false);
      } catch (ClassNotFoundException ex) { 
            Logger.getLogger(DBSetupTask.class.getName()).log(Level.SEVERE, null, ex);
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
//      for (String name: SAMPLE_NAME_DATA) {
//        st.executeUpdate("insert into employee values(1,'" + name + "')");
//      }
      logger.info("Populated database");
    }
    
    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
  }

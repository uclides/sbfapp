/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sbfapp.model.Order;

/**
 *
 * @author Uclides Gil
 */
public class FechOrders extends DBTask<ObservableList<Order>>{
    ObservableList<Order> Orders;
    
    private static final Logger logger = Logger.getLogger(FechOrders.class.getName());
    DB db = new DB();
    
        @Override
        protected ObservableList<Order> call() throws Exception {
           Thread.sleep(1000);
           
           try(Connection con = db.getConnection()){
               return FechAllOrders(con);
           }
        }

        private ObservableList<Order> FechAllOrders(Connection con) throws SQLException {
            logger.info("Fetching names from database");
            ObservableList<Order> orders = FXCollections.observableArrayList();

            Statement st = con.createStatement();      
            ResultSet rs = st.executeQuery("select * from orders");
            while (rs.next()) {
              Order o = new Order(rs.getString("id"),rs.getString("date"),rs.getString("user_id"));
              orders.add(o);
            }
            setOrders(orders);
            logger.log(Level.INFO, "Found {0}", orders.toString());

            return orders;
        }

    public ObservableList<Order> getOrders() {
        return Orders;
    }

    public void setOrders(ObservableList<Order> Orders) {
        this.Orders = Orders;
    }
  
  }

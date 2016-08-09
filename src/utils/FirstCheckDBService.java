/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author Uclides Gil
 */
        public class FirstCheckDBService extends Service<Connection> {
            private String url = new String();
            private String user = new String();
            private String password = new String();

            public final String getUrl() {
                return url;
            }

            public final String getUser() {
                return user;
            }

            public final String getPassword() {
                return password;
            }

            public final void setUrl(String url) {
                this.url = url;
            }

            public final void setUser(String user) {
                this.user = user;
            }

            public final void setPassword(String password) {
                this.password = password;
            }

            @Override
            protected Task<Connection> createTask() {
            final String _url = getUrl();
            final String _user = getUser();
            final String _password = getPassword();
            return new Task<Connection>() {
                @Override
                protected Connection call() {        
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        
                        return con;
                    } catch (SQLException ex) {
                        System.out.println("ERROR AL CONECTAR");
                        return null;
                    }
            
                }
            };
        }
    }

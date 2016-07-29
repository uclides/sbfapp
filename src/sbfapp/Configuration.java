/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbfapp;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
/**
 *
 * @author Uclides Gil
 */
public class Configuration {
    
    //read file type json with pass the path
    public void readFile(String file){
        String result;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            
            String line = br.readLine();
            
            while (line != null) {                
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
            System.out.println(result);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

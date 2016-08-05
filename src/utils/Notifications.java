/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Uclides Gil
 */
public class Notifications {
    
    
    public void createNotDesktop(Image image, String title, String message,
            Paint paint, AnimationType animationType, int duration){
    
                Image img = image;
        
                
        TrayNotification tray = new TrayNotification();
        
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setRectangleFill(paint);
        tray.setAnimationType(animationType);
        tray.setImage(img);
        tray.showAndDismiss(Duration.seconds(duration));
    }
    
}

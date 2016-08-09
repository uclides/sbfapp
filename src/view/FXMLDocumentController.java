/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.skins.JFXTableColumnHeader;
import com.jfoenix.skins.JFXTableHeaderRow;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javax.naming.Binding;
import jiconfont.icons.FontAwesome;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;
import sbfapp.Sbfapp;
import sbfapp.model.Order;
import utils.Notifications;

/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    Notifications notifications = new Notifications();
    Sbfapp sbfapp;

    @FXML private JFXButton b_connection;   
    @FXML private JFXButton b_getOrders;
    @FXML private JFXButton b_exit;
    @FXML private JFXListView lv_type_order;
    @FXML private JFXTreeTableView<Order> jFXTreeTableView;   
    @FXML private JFXTreeTableColumn<Order,String> jFXTreeTableColumn1;   
    @FXML private JFXTreeTableColumn<Order,String> jFXTreeTableColumn2;   
    @FXML private JFXTreeTableColumn<Order,String> jFXTreeTableColumn3;
    
    @FXML Label treeTableViewCount;
    @FXML Label editableTreeTableViewCount;
    @FXML JFXTextField searchField;
    @FXML JFXTextField searchField2;
    
    Label lbl_order_new, lbl_order_process, lbl_order_completed, lbl_order_cancel;     
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

    }
    @FXML
    private void exitApp(ActionEvent event) throws Exception{
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            IconFontFX.register(GoogleMaterialDesignIcons.getIconFont());
            
            setIconToButtom(b_connection, new IconNode(GoogleMaterialDesignIcons.AUTORENEW), "verifica tu conexión a base de datos");
            setIconToButtom(b_getOrders, new IconNode(GoogleMaterialDesignIcons.GET_APP), "obtener todas las ordenes");
            setIconToButtom(b_exit, new IconNode(GoogleMaterialDesignIcons.EXIT_TO_APP), "salir");
            //init values for table
            jFXTreeTableColumn1.setCellValueFactory((TreeTableColumn.CellDataFeatures<Order, String> param) ->{
                if(jFXTreeTableColumn1.validateValue(param)) return param.getValue().getValue().getId();
                else return jFXTreeTableColumn1.getComputedValue(param);
            });
            jFXTreeTableColumn2.setCellValueFactory((TreeTableColumn.CellDataFeatures<Order, String> param) ->{
                if(jFXTreeTableColumn2.validateValue(param)) return param.getValue().getValue().getDate();
                else return jFXTreeTableColumn2.getComputedValue(param);
            });
            
            jFXTreeTableColumn3.setCellValueFactory((TreeTableColumn.CellDataFeatures<Order, String> param) ->{
                if(jFXTreeTableColumn3.validateValue(param)) return param.getValue().getValue().getUser_id();
                else return jFXTreeTableColumn3.getComputedValue(param);
            });
            lbl_order_new = new Label("nuevas");

            lbl_order_process = new Label("en proceso");
            lbl_order_completed = new Label("completadas");
            lbl_order_cancel = new Label("canceladas");
            
            lv_type_order.getItems().addAll(lbl_order_new, lbl_order_process,
                    lbl_order_completed, lbl_order_cancel);
//            fechOrders.run();
//            
//            ObservableList<Order> orders = fechOrders.getOrders();
//            
//            //get init elemens DB
//
//            final TreeItem<Order> root = new RecursiveTreeItem<>(orders, RecursiveTreeObject::getChildren);
//            
//            jFXTreeTableView.setRoot(root);
//            jFXTreeTableView.setShowRoot(false);
//            jFXTreeTableView.setEditable(true);
//            
//            jFXTreeTableView.getColumns().setAll(jFXTreeTableColumn1, jFXTreeTableColumn2, jFXTreeTableColumn3);
        
    }    

    public void validateGui(boolean bool){
        b_connection.setDisable(bool);
        b_getOrders.setDisable(!bool);
         notifications.showStatusDB(bool);
    }
    
    public void setIconToButtom(JFXButton button, IconNode iconNode,String tooltip){
        IconNode icon = iconNode;
        iconNode.setIconSize(30);
        iconNode.setFill(Color.web("#CFD8DC"));
        button.setGraphic(iconNode);
        button.setTooltip(new Tooltip(tooltip));
    }
    
    
    
    
    
    
    
}

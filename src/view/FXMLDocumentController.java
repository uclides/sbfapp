/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
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
import sbfapp.model.Order;
import utils.DB;

/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    //get object to connect DB
    DB conBd =new DB();
    
    ResultSet rs;
    
    @FXML private JFXButton b_connection;   
    @FXML private JFXTreeTableView<Order> jFXTreeTableView;   
    @FXML private JFXTreeTableColumn<Order,String> jFXTreeTableColumn1;   
    @FXML private JFXTreeTableColumn<Order,String> jFXTreeTableColumn2;   
    @FXML private JFXTreeTableColumn<Order,String> jFXTreeTableColumn3;
    
    @FXML Label treeTableViewCount;
    @FXML Label editableTreeTableViewCount;
    @FXML JFXTextField searchField;
    @FXML JFXTextField searchField2;
        
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
           conBd.initParameterDB();    

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            IconFontFX.register(GoogleMaterialDesignIcons.getIconFont());
            
            IconNode iconNode = new IconNode(GoogleMaterialDesignIcons.AUTORENEW);
            iconNode.setIconSize(30);
            iconNode.setFill(Color.web("#607D8B"));
            b_connection.setGraphic(iconNode);
            
            
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
            
            ObservableList<Order> orders = FXCollections.observableArrayList();
            
            orders.add(new Order("Computer Department", "23","CD 1"));
            orders.add(new Order("Sales Department", "22","Employee 1"));
            orders.add(new Order("Sales Department", "22","Employee 2"));
            orders.add(new Order("Sales Department", "25","Employee 4"));
            orders.add(new Order("Sales Department", "25","Employee 5"));
            orders.add(new Order("IT Department", "42","ID 2"));
            orders.add(new Order("HR Department", "22","HR 1"));
            
            
            final TreeItem<Order> root = new RecursiveTreeItem<>(orders, RecursiveTreeObject::getChildren);
            
            jFXTreeTableView.setRoot(root);
            jFXTreeTableView.setShowRoot(false);
            jFXTreeTableView.setEditable(true);
            
            
            jFXTreeTableView.getColumns().setAll(jFXTreeTableColumn1, jFXTreeTableColumn2, jFXTreeTableColumn3);
        
    }    
    
}

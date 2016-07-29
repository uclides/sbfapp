/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbfapp.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Uclides Gil
 */
public class Order extends RecursiveTreeObject<Order> {
    StringProperty  id;
    StringProperty  date;
    StringProperty  user_id;
    StringProperty  pay_id;
    StringProperty  total_product;
    StringProperty  mount;
    StringProperty  status_id;
    StringProperty  creted_at;
    StringProperty  update_at;
    
    public Order(String id, String date, String user_id, String pay_id,
            String total_product, String mount, String status_id,
            String created_at, String update_at){
        
        this.id = new SimpleStringProperty(id);
        this.date = new SimpleStringProperty(date);
        this.user_id = new SimpleStringProperty(user_id);
        this.pay_id = new SimpleStringProperty(pay_id);
        this.total_product = new SimpleStringProperty(total_product);
        this.mount = new SimpleStringProperty(mount);
        this.status_id = new SimpleStringProperty(status_id);
        this.creted_at = new SimpleStringProperty(created_at);
        this.update_at = new SimpleStringProperty(update_at);
    }
    
    public Order(String id, String date, String user_id){
        
        this.id = new SimpleStringProperty(id);
        this.date = new SimpleStringProperty(date);
        this.user_id = new SimpleStringProperty(user_id);
    }

    public StringProperty getId() {
        return id;
    }

    public StringProperty getDate() {
        return date;
    }

    public StringProperty getUser_id() {
        return user_id;
    }

    public StringProperty getPay_id() {
        return pay_id;
    }

    public StringProperty getTotal_product() {
        return total_product;
    }

    public StringProperty getMount() {
        return mount;
    }

    public StringProperty getStatus_id() {
        return status_id;
    }

    public StringProperty getCreted_at() {
        return creted_at;
    }

    public StringProperty getUpdate_at() {
        return update_at;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setUser_id(String user_id) {
        this.user_id.set(user_id);
    }

    public void setPay_id(String pay_id) {
        this.pay_id.set(pay_id);
    }

    public void setTotal_product(String total_product) {
        this.total_product.set(total_product);
    }

    public void setMount(String mount) {
        this.mount.set(mount);
    }

    public void setStatus_id(String status_id) {
        this.status_id.set(status_id);
    }

    public void setCreted_at(String creted_at) {
        this.creted_at.set(creted_at);
    }

    public void setUpdate_at(String update_at) {
        this.update_at.set(update_at);
    }
    
}

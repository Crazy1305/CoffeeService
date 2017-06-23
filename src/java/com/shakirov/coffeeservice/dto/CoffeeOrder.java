package com.shakirov.coffeeservice.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vadim.shakirov
 */

public class CoffeeOrder {
    
    private int id;
    private Timestamp orderDate;
    private String name;
    private String deliveryAddress;
    private double cost;
    private final List<CoffeeOrderItem> items = new ArrayList<>();
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Timestamp getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Timestamp date) {
        this.orderDate = date;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    
    public void setDeliveryAddress(String address) {
        this.deliveryAddress = address;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public List<CoffeeOrderItem> list() {
        return new ArrayList<>(items);
    }
    
    public void add(CoffeeOrderItem item) {
        items.add(item);
    }
    
    public void add(List<CoffeeOrderItem> list) {
        items.addAll(list);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeOrder {
    
    private int id;
    private Date orderDate;
    private String name;
    private String deliveryAddress;
    private double cost;
    private final List<CoffeeOrderItem> items;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date date) {
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
    
    public CoffeeOrder( int id, Date orderDate, 
                        String name, String deliveryAddress, 
                        double cost) 
    {
        this.id = id;
        this.orderDate = orderDate;
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.cost = cost;
        items = new ArrayList<>();
    }

}

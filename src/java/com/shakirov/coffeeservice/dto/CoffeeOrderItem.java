/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dto;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeOrderItem {
    private int id;
    private CoffeeType type;
    private CoffeeOrder order;
    private int quantity;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setType(CoffeeType type) {
        this.type = type;
    }
    
    public CoffeeType getType() {
        return type;
    }
    
    public void setOrder(CoffeeOrder order) {
        this.order = order;
    }
    
    public CoffeeOrder getOrder() {
        return order;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public CoffeeOrderItem(int id
            , CoffeeType type
            , CoffeeOrder order
            , int quantity) {
        this.id = id;
        this.type = type;
        this.order = order;
        this.quantity = quantity;
    }
    
}

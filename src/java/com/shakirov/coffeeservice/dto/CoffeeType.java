/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dto;

import java.util.Formatter;
import java.util.Locale;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeType {
    private int id;
    private String name;
    private double price;
    private char disabled;
    
    public CoffeeType(int id, String name, double price, char disabled) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.disabled = disabled;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public char getDisabled() {
        return disabled;
    }
    
}

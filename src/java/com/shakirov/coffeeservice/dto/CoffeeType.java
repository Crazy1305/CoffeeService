package com.shakirov.coffeeservice.dto;

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

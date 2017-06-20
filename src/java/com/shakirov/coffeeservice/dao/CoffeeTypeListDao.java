/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dao;

import com.shakirov.coffeeservice.dto.CoffeeType;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vadim.shakirov
 */
public abstract class CoffeeTypeListDao {
    
    public abstract CoffeeType getItemById(int id) throws ElementNotFoundException;
    public abstract List<CoffeeType> list();
    public abstract int count();
    public abstract int create(CoffeeType newInstance) throws SQLException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dao;

import com.shakirov.coffeeservice.dto.CoffeeOrderItem;
import java.sql.SQLException;

/**
 *
 * @author vadim.shakirov
 */
public abstract class OrderItemDao {
    
    public abstract int create(CoffeeOrderItem newInstance) throws SQLException;
    
}

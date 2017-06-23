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

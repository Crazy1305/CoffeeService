package com.shakirov.coffeeservice.dao;

import com.shakirov.coffeeservice.dto.CoffeeOrder;
import com.shakirov.coffeeservice.dto.CoffeeOrderItem;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vadim.shakirov
 */
public abstract class CoffeeOrderDao {

    protected CoffeeOrder order = new CoffeeOrder();

    public abstract int create(CoffeeOrder newInstance) throws SQLException;

    public void addItem(CoffeeOrderItem item) {
        order.add(item);
    }

    public CoffeeOrder getOrder() {
        return order;
    }
    
    public List<CoffeeOrderItem> items() {
        return order.list();
    }

}

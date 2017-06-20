/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    protected CoffeeOrder order;

    public abstract int create(CoffeeOrder newInstance) throws SQLException;

    public abstract List<CoffeeOrderItem> items();

    public void addItem(CoffeeOrderItem item) {
        order.add(item);
    }

    public CoffeeOrder getOrder() {
        return order;
    }

}

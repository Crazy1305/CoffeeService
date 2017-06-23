package com.shakirov.coffeeservice.dao.jdbc;

import com.shakirov.coffeeservice.dao.CoffeeOrderDao;
import com.shakirov.coffeeservice.dao.CoffeeTypeListDao;
import com.shakirov.coffeeservice.dao.DaoFactory;
import com.shakirov.coffeeservice.dao.OrderItemDao;
import java.sql.SQLException;


/**
 *
 * @author vadim.shakirov
 */
public class DaoFactoryImpl extends DaoFactory {

    @Override
    public CoffeeTypeListDao getCoffeeTypeList() throws SQLException {
        return new CoffeeTypeListDaoImpl();
    }

    @Override
    public CoffeeOrderDao getCoffeeOrderDao() throws SQLException {
        return new CoffeeOrderDaoImpl();
    }

    @Override
    public OrderItemDao getOrderItemDao() throws SQLException {
        return new OrderItemDaoImpl();
    }
    
}

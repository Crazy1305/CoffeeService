/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dao.jdbc;

import com.shakirov.coffeeservice.dao.CoffeeOrderDao;
import com.shakirov.coffeeservice.dao.CoffeeTypeListDao;
import com.shakirov.coffeeservice.dao.DaoFactory;
import com.shakirov.coffeeservice.dao.OrderItemDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author vadim.shakirov
 */
public class DaoFactoryImpl extends DaoFactory {
    
    private static Connection connection;    
  
    @Override
    public Connection getConnection() throws JdbcConnectError {
        try {
            if ((connection == null)||(!connection.isValid(0))) {
                loadProperties();
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DaoFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new JdbcConnectError();
        }
        return connection;
    }

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

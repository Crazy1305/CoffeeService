/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dao;

import com.shakirov.coffeeservice.dao.jdbc.DaoFactoryImpl;
import com.shakirov.coffeeservice.dao.jdbc.JdbcConnectError;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author vadim.shakirov
 */
public abstract class DaoFactory {
    private static final DaoFactory factory = new DaoFactoryImpl();
    
    protected String driver;
    protected String url;
    protected String user;
    protected String password;
    
    protected void loadProperties() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/db.properties"));
        driver = properties.getProperty("db.driver");
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.login");
        password = properties.getProperty("db.password");
    }
    
    public static DaoFactory getInstance() { 
        return factory;
    }
    
    public abstract Connection getConnection() throws JdbcConnectError;
    public abstract CoffeeTypeListDao getCoffeeTypeList() throws SQLException;
    public abstract CoffeeOrderDao getCoffeeOrderDao() throws SQLException;
    public abstract OrderItemDao getOrderItemDao() throws SQLException;
}

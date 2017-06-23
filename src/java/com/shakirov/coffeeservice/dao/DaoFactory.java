package com.shakirov.coffeeservice.dao;

import java.sql.SQLException;
/**
 *
 * @author vadim.shakirov
 */

public abstract class DaoFactory {
    public static final int JPA = 1;
    public static final int JDBC = 2;
    
    private static DaoFactory factory = null;

    public static DaoFactory getInstance(int source) { 
        if (factory == null) {
            switch (source) {
                case JPA : factory = new com.shakirov.coffeeservice.dao.jpa.DaoFactoryImpl();
                case JDBC : factory = new com.shakirov.coffeeservice.dao.jdbc.DaoFactoryImpl();
            }
        }
        return factory;
    }
    
    public abstract CoffeeTypeListDao getCoffeeTypeList() throws SQLException;
    public abstract CoffeeOrderDao getCoffeeOrderDao() throws SQLException;
    public abstract OrderItemDao getOrderItemDao() throws SQLException;
}

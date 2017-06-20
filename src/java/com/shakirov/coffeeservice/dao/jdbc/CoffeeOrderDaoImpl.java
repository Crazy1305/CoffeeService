/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dao.jdbc;

import com.shakirov.coffeeservice.dao.CoffeeOrderDao;
import com.shakirov.coffeeservice.dao.DaoFactory;
import com.shakirov.coffeeservice.dao.OrderItemDao;
import com.shakirov.coffeeservice.dto.CoffeeOrder;
import com.shakirov.coffeeservice.dto.CoffeeOrderItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeOrderDaoImpl extends CoffeeOrderDao {
    
    private final DaoFactory factory;
    
    private static final String GET_ID = "select max(id) as lastid from coffeeorder";
    private static final String CREATE = "insert into coffeeorder values (?, ?, ?, ?, ?)";
  
    private int getLastId() throws JdbcConnectError {
        try {
            Connection connection = factory.getConnection();
            Statement st = connection.createStatement();
            if (st.execute(GET_ID)) {
                ResultSet set = st.getResultSet();
                set.next();
                return set.getInt("lastid");
            }
        } catch (SQLException e) {
            Logger.getLogger(CoffeeOrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    public CoffeeOrderDaoImpl() throws SQLException {
        factory = (DaoFactory) new DaoFactoryImpl();
        order = new CoffeeOrder(0, new Date(Calendar.getInstance().getTimeInMillis()), "", "", 0);
    }

    @Override
    public int create(CoffeeOrder newInstance) throws SQLException {
        Connection connection = null;
        try {
            connection = factory.getConnection();
            connection.setAutoCommit(false);
            int id = getLastId() + 1;
            newInstance.setId(id);
            PreparedStatement ps = connection.prepareStatement(CREATE);
            ps.setInt(1, newInstance.getId());
            ps.setDate(2, newInstance.getOrderDate());
            ps.setString(3, newInstance.getName());
            ps.setString(4, newInstance.getDeliveryAddress());
            ps.setDouble(5, newInstance.getCost());
            ps.executeUpdate();
            for (int i = 0; i < order.list().size(); i++) {
                order.list().get(i).setOrder(newInstance);
                OrderItemDao orderItem = DaoFactory.getInstance().getOrderItemDao();
                orderItem.create(order.list().get(i));
            }
            connection.commit();
            connection.setAutoCommit(true);
            return id;
        } catch (JdbcConnectError e) {
            if (connection != null) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            Logger.getLogger(CoffeeOrderDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new SQLException("Database connect error.");
        }
    }

    @Override
    public List<CoffeeOrderItem> items() {
        return order.list();
    }

}

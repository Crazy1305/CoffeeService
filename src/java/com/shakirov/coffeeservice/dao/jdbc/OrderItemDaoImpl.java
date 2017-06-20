/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dao.jdbc;

import com.shakirov.coffeeservice.dto.CoffeeOrderItem;
import com.shakirov.coffeeservice.dao.DaoFactory;
import com.shakirov.coffeeservice.dao.OrderItemDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vadim.shakirov
 */
public class OrderItemDaoImpl extends OrderItemDao {
    
    private static final String GET_ID = "select max(id) as lastid from coffeeorderitem";
    private static final String CREATE = "insert into coffeeorderitem values (?, ?, ?, ?)";
    /*private static final String READ = "select * from coffeeorderitem where id = ?";
    private static final String UPDATE = "update coffeeorderitem set type_id = ?, " +
            "order_id = ?, quantity = ? where id = ?";
    private static final String DELETE = "delete from coffeeorderitem where id = ?";*/

    private int getLastId() throws JdbcConnectError {
        try {
            Connection connection = DaoFactory.getInstance().getConnection();
            Statement st = connection.createStatement();
            if (st.execute(GET_ID)) {
                ResultSet set = st.getResultSet();
                set.next();
                return set.getInt("lastid");
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderItemDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    @Override
    public int create(CoffeeOrderItem newInstance) throws SQLException {
        try {
            Connection connection = DaoFactory.getInstance().getConnection();
            int id = getLastId() + 1;
            newInstance.setId(id);
                        
            PreparedStatement ps = connection.prepareStatement(CREATE);
            ps.setInt(1, newInstance.getId());
            ps.setInt(2, newInstance.getType().getId());
            ps.setInt(3, newInstance.getOrder().getId());
            ps.setInt(4, newInstance.getQuantity());
            ps.executeUpdate();
            return id;
        } catch (JdbcConnectError e) {
            Logger.getLogger(OrderItemDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        throw new SQLException("Database connect error.");
    }

    /*@Override
    public CoffeeOrderItem getById(Integer id) throws SQLException {
        try {
            Connection connection = DaoFactory.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(READ);
            ps.setInt(1, id);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                CoffeeOrderItem result = new CoffeeOrderItem(
                        set.getInt("id"), 
                        null,
                        null,
                        set.getInt("quantity"));
                return result;
            } else {
                return null;
            }
        } catch (JdbcConnectError e) {
            Logger.getLogger(OrderItemListDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new SQLException("Database connect error.");
        }
    }

    @Override
    public void update(CoffeeOrderItem item) throws SQLException {
        try {
            Connection connection = DaoFactory.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, item.getType().getId());
            ps.setInt(2, item.getOrder().getId());
            ps.setInt(3, item.getQuantity());
            ps.setInt(4, item.getId());
            if (ps.executeUpdate() == 0) 
                throw new SQLException("CoffeeType update error. " + item.toString());
        } catch (JdbcConnectError e) {
            Logger.getLogger(OrderItemListDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new SQLException("Database connect error.");
        }
    }

    @Override
    public void delete(CoffeeOrderItem item) throws SQLException {
        try {
            Connection connection = DaoFactory.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, item.getId());
            if (ps.executeUpdate() == 0) 
                throw new SQLException("CoffeeType delete error. " + item.toString());
        } catch (JdbcConnectError e) {
            Logger.getLogger(OrderItemListDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new SQLException("Database connect error.");
        }
    }*/
    
    
}

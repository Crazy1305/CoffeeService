/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shakirov.coffeeservice.dao.jdbc;

import com.shakirov.coffeeservice.dao.CoffeeTypeListDao;
import com.shakirov.coffeeservice.dao.DaoFactory;
import com.shakirov.coffeeservice.dao.ElementNotFoundException;
import com.shakirov.coffeeservice.dto.CoffeeType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeTypeListDaoImpl extends CoffeeTypeListDao {
    private final DaoFactory factory;
    private List<CoffeeType> items;
    
    private static final String GET_ID = "select max(id) as lastid from coffeetype";
    private static final String SELECT = "select * from coffeetype";
    private static final String CREATE = "insert into coffeetype values (?, ?, ?, ?)";
    
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
            Logger.getLogger(CoffeeTypeListDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    public CoffeeTypeListDaoImpl() throws SQLException {
        factory = (DaoFactory) new DaoFactoryImpl();
        items = new ArrayList<>();
        try {
            Connection connection = factory.getConnection();
            Statement st = connection.createStatement();
            if (st.execute(SELECT)) {
                ResultSet set = st.getResultSet();
                while (set.next()) {
                    CoffeeType result = new CoffeeType(
                        set.getInt("id"), 
                        set.getString("type_name"),
                        set.getDouble("price"),
                        set.getString("disabled").charAt(0));
                    items.add(result);
                }
            }
        } catch (JdbcConnectError e) {
            Logger.getLogger(CoffeeTypeListDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new SQLException("Database connect error.");
        }
    }

    @Override
    public int create(CoffeeType newInstance) throws SQLException {
        try {
            Connection connection = factory.getConnection();
            int id = getLastId() + 1;
            newInstance.setId(id);
            PreparedStatement ps = connection.prepareStatement(CREATE);
            ps.setInt(1, newInstance.getId());
            ps.setString(2, newInstance.getName());
            ps.setDouble(3, newInstance.getPrice());
            ps.setString(4, String.valueOf(newInstance.getDisabled()));
            ps.executeUpdate();
            return id;
        } catch (JdbcConnectError e) {
            Logger.getLogger(CoffeeTypeListDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new SQLException("Database connect error.");
        }
    }

    @Override
    public List<CoffeeType> list() {
        return new ArrayList<>(items);
    }

    @Override
    public int count() {
        return items.size();
    }

    @Override
    public CoffeeType getItemById(int id) throws ElementNotFoundException {
        for (CoffeeType item: items) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new ElementNotFoundException();
    }

}

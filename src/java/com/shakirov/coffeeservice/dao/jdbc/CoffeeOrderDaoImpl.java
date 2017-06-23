package com.shakirov.coffeeservice.dao.jdbc;

import com.shakirov.coffeeservice.dao.CoffeeOrderDao;
import com.shakirov.coffeeservice.dao.DaoFactory;
import com.shakirov.coffeeservice.dao.OrderItemDao;
import com.shakirov.coffeeservice.dto.CoffeeOrder;
import com.shakirov.coffeeservice.utils.JdbcConnectionUtil;
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
public class CoffeeOrderDaoImpl extends CoffeeOrderDao {
    
    private static final String GET_ID = "select max(id) as lastid from coffeeorder";
    private static final String CREATE = "insert into coffeeorder values (?, ?, ?, ?, ?)";
  
    private int getLastId() throws JdbcConnectError {
        try {
            Connection connection = JdbcConnectionUtil.getConnection();
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

    @Override
    public int create(CoffeeOrder newInstance) throws SQLException {
        Connection connection = null;
        try {
            connection = JdbcConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            int id = getLastId() + 1;
            newInstance.setId(id);
            PreparedStatement ps = connection.prepareStatement(CREATE);
            ps.setInt(1, newInstance.getId());
            ps.setObject(2, newInstance.getOrderDate());
            ps.setString(3, newInstance.getName());
            ps.setString(4, newInstance.getDeliveryAddress());
            ps.setDouble(5, newInstance.getCost());
            ps.executeUpdate();
            for (int i = 0; i < order.list().size(); i++) {
                order.list().get(i).setOrder(newInstance);
                OrderItemDao orderItem = DaoFactory.getInstance(DaoFactory.JDBC).getOrderItemDao();
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

}

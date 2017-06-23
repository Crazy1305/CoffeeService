package com.shakirov.coffeeservice.dao.jdbc;

import com.shakirov.coffeeservice.dto.CoffeeOrderItem;
import com.shakirov.coffeeservice.dao.OrderItemDao;
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
public class OrderItemDaoImpl extends OrderItemDao {
    
    private static final String GET_ID = "select max(id) as lastid from coffeeorderitem";
    private static final String CREATE = "insert into coffeeorderitem values (?, ?, ?, ?)";

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
            Logger.getLogger(OrderItemDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    @Override
    public int create(CoffeeOrderItem newInstance) throws SQLException {
        try {
            Connection connection = JdbcConnectionUtil.getConnection();
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
    
}

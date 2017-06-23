package com.shakirov.coffeeservice.utils;

import com.shakirov.coffeeservice.dao.jdbc.JdbcConnectError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vadim.shakirov
 */
public class JdbcConnectionUtil {
    
    private static Connection connection = null; 
    
    public static Connection getConnection() throws JdbcConnectError {
        if (connection == null) {
            if (!"".equals(PropertiesUtil.getDriver())) {
                try {
                    Class.forName(PropertiesUtil.getDriver());
                    connection = DriverManager.getConnection(
                            PropertiesUtil.getUrl()
                            , PropertiesUtil.getUser()
                            , PropertiesUtil.getPassword());
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(JdbcConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
                    throw new JdbcConnectError();
                }
            }
        }
        return connection;
    }
    
}

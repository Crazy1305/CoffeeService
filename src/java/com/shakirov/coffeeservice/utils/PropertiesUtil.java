package com.shakirov.coffeeservice.utils;

import com.shakirov.coffeeservice.dao.DaoFactory;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vadim.shakirov
 */
class PropertiesUtil {
    
    private static String driver = "";
    private static String url = "";
    private static String user = "";
    private static String password = "";
    private static final int DATA_SOURCE = DaoFactory.JPA;
    
    static {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesUtil.class.getResourceAsStream("/db.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver = properties.getProperty("db.driver");
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.login");
        password = properties.getProperty("db.password");
    }
    
    
    
    public static String getDriver() { return driver; }
    public static String getUrl() { return url; }
    public static String getUser() { return user; }
    public static String getPassword() { return password; }
    public static int getDataSource() { return DATA_SOURCE; }
    
}

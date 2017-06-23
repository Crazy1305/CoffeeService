package com.shakirov.coffeeservice.utils;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author vadim.shakirov
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration conf = new Configuration()
                    .addClass(com.shakirov.coffeeservice.dto.CoffeeOrder.class)
                    .addClass(com.shakirov.coffeeservice.dto.CoffeeOrderItem.class)
                    .addClass(com.shakirov.coffeeservice.dto.CoffeeType.class)
                    .configure();
            Properties props = new Properties();
            props.setProperty("hibernate.connection.driver_class", PropertiesUtil.getDriver());
            props.setProperty("hibernate.connection.url", PropertiesUtil.getUrl());
            props.setProperty("hibernate.connection.username", PropertiesUtil.getUser());
            props.setProperty("hibernate.connection.password", PropertiesUtil.getPassword());
            
            conf.addProperties(props);
            ServiceRegistry reg = new StandardServiceRegistryBuilder()
                    .applySettings(conf.getProperties())
                    .build();
            sessionFactory = conf.buildSessionFactory(reg);
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

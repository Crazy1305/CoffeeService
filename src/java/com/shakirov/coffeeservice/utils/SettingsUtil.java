package com.shakirov.coffeeservice.utils;

import com.shakirov.coffeeservice.dao.DaoFactory;

/**
 *
 * @author vadim.shakirov
 */
public class SettingsUtil {
    private static final int DATA_SOURCE = DaoFactory.JPA;
    
    public static int getDataSource() { return DATA_SOURCE; }
    
}

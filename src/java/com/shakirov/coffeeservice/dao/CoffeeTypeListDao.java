package com.shakirov.coffeeservice.dao;

import com.shakirov.coffeeservice.dto.CoffeeType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vadim.shakirov
 */
public abstract class CoffeeTypeListDao {
    
    protected List<CoffeeType> items;
    
    public abstract int create(CoffeeType newInstance) throws SQLException;

    public CoffeeType getItemById(int id) throws ElementNotFoundException {
        for (CoffeeType item: items) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new ElementNotFoundException();
    }
    
    public List<CoffeeType> list() {
        return new ArrayList<>(items);
    }
    
    public int count() {
        return items.size();
    }
    
}

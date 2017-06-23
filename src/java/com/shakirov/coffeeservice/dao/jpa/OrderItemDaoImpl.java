package com.shakirov.coffeeservice.dao.jpa;

import com.shakirov.coffeeservice.dao.OrderItemDao;
import com.shakirov.coffeeservice.dto.CoffeeOrderItem;
import com.shakirov.coffeeservice.utils.HibernateUtil;
import java.sql.SQLException;
import org.hibernate.Session;

/**
 *
 * @author vadim.shakirov
 */
public class OrderItemDaoImpl extends OrderItemDao {

    @Override
    public int create(CoffeeOrderItem newInstance) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int id = (int) session.save(newInstance);
        session.close();
        return id;
    }
    
}

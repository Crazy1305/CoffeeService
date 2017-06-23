package com.shakirov.coffeeservice.dao.jpa;

import com.shakirov.coffeeservice.dao.CoffeeOrderDao;
import com.shakirov.coffeeservice.dto.CoffeeOrder;
import com.shakirov.coffeeservice.utils.HibernateUtil;
import java.sql.SQLException;
import org.hibernate.Session;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeOrderDaoImpl extends CoffeeOrderDao{

    @Override
    public int create(CoffeeOrder newInstance) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int id = (int) session.save(newInstance);
        session.close();
        return id;
    }
    
}

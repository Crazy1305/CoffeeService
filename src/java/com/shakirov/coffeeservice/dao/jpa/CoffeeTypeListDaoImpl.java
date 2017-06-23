package com.shakirov.coffeeservice.dao.jpa;

import com.shakirov.coffeeservice.dao.CoffeeTypeListDao;
import com.shakirov.coffeeservice.dto.CoffeeType;
import com.shakirov.coffeeservice.utils.HibernateUtil;
import java.sql.SQLException;
import org.hibernate.Session;

/**
 *
 * @author vadim.shakirov
 */
public class CoffeeTypeListDaoImpl extends CoffeeTypeListDao {

    @Override
    public int create(CoffeeType newInstance) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int id = (int) session.save(newInstance);
        session.close();
        return id;
    }
    
}

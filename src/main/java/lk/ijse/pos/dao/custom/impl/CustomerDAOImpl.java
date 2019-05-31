package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudDAOImpl;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {

    public CustomerDAOImpl(){
        super();
    }

    @Override
    public int count() throws Exception {
        return (int) entityManager.createNativeQuery("SELECT COUNT(*) FROM Customer", Integer.class)
                .getSingleResult();
    }
}

package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemBOImpl implements ItemBO {



    @Autowired
    private ItemDAO itemDAO;

    public List<ItemDTO> getAllItems() throws Exception {

        EntityManager entityManager =HibernateUtil.buildEntityManager();
        entityManager.getTransaction().begin();
            itemDAO.setEntityManager(entityManager);
            List<ItemDTO> items = itemDAO.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());
            entityManager.getTransaction().commit();
            return items;

    }

    public void saveItem(ItemDTO item) throws Exception {
        EntityManager entityManager = HibernateUtil.buildEntityManager();
        entityManager.getTransaction().begin();
            itemDAO.setEntityManager(entityManager);
            itemDAO.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            entityManager.getTransaction().commit();

    }

    public void updateItem(ItemDTO item) throws Exception {
        EntityManager entityManager = HibernateUtil.buildEntityManager();
        entityManager.getTransaction().begin();
            itemDAO.setEntityManager(entityManager);
            itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            entityManager.getTransaction().commit();
    }

    public void deleteItem(String code) throws Exception {
        EntityManager entityManager = HibernateUtil.buildEntityManager();
        entityManager.getTransaction().begin();
            itemDAO.setEntityManager(entityManager);
            itemDAO.delete(code);
            entityManager.getTransaction().commit();
    }

}

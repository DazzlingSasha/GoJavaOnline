package restaurant.controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Ingredient;
import restaurant.model.Warehouse;

import java.util.List;

public class WarehouseController implements MainMethodControllers<Warehouse>{
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Warehouse item) {
        LOGGER.info("Add new items on Warehouse!");
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> selectAll() {
        LOGGER.info("Select all items with Warehouse with JOIN Ingredients!");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select w from Warehouse w").list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(Warehouse warehouse) {
        LOGGER.info("Delete item with Warehouse, where id: "+ warehouse.getId());
//        warehouseDao.deleteItemsWithWarehouse(id);
        Session session = sessionFactory.getCurrentSession();
        session.delete(warehouse);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Warehouse item) {
        LOGGER.info("Update item in Warehouse!");
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> findByName(String nameItems) {
        LOGGER.info("Find user by name item where name = " + nameItems);
//        return warehouseDao.findByNameWarehouse(nameItems);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT w FROM Warehouse w WHERE w.idIngredient.name like :nameItems");
        query.setParameter("nameItems", "%"+nameItems+"%");
        return query.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Warehouse findById(int id) {
        LOGGER.info("Find user by name item where id = " + id);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select w from Warehouse w where w.id =:id");
        query.setParameter("id", id);
        return (Warehouse) query.uniqueResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> findEndsItemsInWarehouse() {
        LOGGER.info("Find items in warehouse where quantity < 10 with JOIN Ingredients");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT w FROM Warehouse w WHERE w.quantity < 10").list();
//        return warehouseDao.endsItemsInWarehouseJOINIngredients();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

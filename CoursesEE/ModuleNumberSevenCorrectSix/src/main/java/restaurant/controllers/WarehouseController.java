package restaurant.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Warehouse;
import restaurant.model.Hibernate.WarehouseDao;

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
    public void deleteWithDatabase(Warehouse id) {
        LOGGER.info("Delete item with Warehouse, where id: "+ id.getId());
//        warehouseDao.deleteItemsWithWarehouse(id);
        Session session = sessionFactory.getCurrentSession();
        session.delete(id);
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
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Warehouse findById(int id) {
        LOGGER.info("Find user by name item where id = " + id);
        Session session = sessionFactory.getCurrentSession();
        return (Warehouse) session.createQuery("select w from Warehouse w where Warehouse.id ="+id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Warehouse> findEndsItemsInWarehouse() {
        LOGGER.info("Find items in warehouse where quantity < 10 with JOIN Ingredients");
//        return warehouseDao.endsItemsInWarehouseJOINIngredients();
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

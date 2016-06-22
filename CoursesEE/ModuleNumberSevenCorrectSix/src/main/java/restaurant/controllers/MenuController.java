package restaurant.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Menu;
import restaurant.model.Hibernate.MenuDao;

import java.util.List;

public class MenuController implements MainMethodControllers<Menu> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Menu item) {
        LOGGER.info("Add new create category menu");
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Menu> selectAll() {
        LOGGER.info("Select all category menu");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select m from Menu m").list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(Menu item) {
        LOGGER.info("Delete menu category with id: " + item.getId());
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Menu item) {
        LOGGER.info("Update category menu with id: " + item.getId());
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    @Override
    public Menu findById(int id) {
        return null;
    }

    @Override
    public List<Menu> findByName(String name) {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

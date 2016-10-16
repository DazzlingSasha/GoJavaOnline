package restaurant.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Menu;

import java.util.List;

public class MenuDao implements MainMethodDao<Menu> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDao.class);
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
        LOGGER.info("Select menu by id:" + id);
        Session session = sessionFactory.getCurrentSession();
        Menu menu = (Menu) session.load(Menu.class, new Integer(id));
        LOGGER.info("Find by id:" + menu);
        return menu;
    }

    @Override
    public List<Menu> findByName(String name) {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

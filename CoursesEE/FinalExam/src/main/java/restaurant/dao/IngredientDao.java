package restaurant.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Ingredient;

import java.util.List;

public class IngredientDao implements MainMethodDao<Ingredient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientDao.class);
    private SessionFactory sessionFactory;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Ingredient item) {
        LOGGER.info("Add new Ingredient!");
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ingredient> selectAll() {
        LOGGER.info("Select all Ingredients!");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select i from Ingredient i").list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(Ingredient item) {
        LOGGER.info("Delete ingredients by id: "+ item.getName());
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Ingredient item) {
        LOGGER.info("Update ingredients! ");
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Ingredient findById(int id) {
        LOGGER.info("Select ingredients by id: "+ id);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.id =:id");
        query.setParameter("id", id);
        return (Ingredient) query.uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ingredient> findByName(String name) {
        LOGGER.info("Select ingredients by name: "+ name);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.name like :name");
        query.setParameter("name", "%"+name+"%");
        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

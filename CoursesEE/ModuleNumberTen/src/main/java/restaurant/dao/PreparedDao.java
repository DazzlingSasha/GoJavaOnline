package restaurant.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Cook;
import restaurant.model.PreparedDish;

import java.util.List;

public class PreparedDao implements MainMethodDao<PreparedDish> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedDao.class);
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(PreparedDish item) {
        LOGGER.info("Add new prepared Dish in Database to order №" + item.getIdOrder());
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        session.flush();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAll() {
        LOGGER.info("Select all dishes prepared and not prepared");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select pd from PreparedDish pd").list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(PreparedDish preparedDish) {
        LOGGER.info("Delete prepared dish by id: "+ preparedDish.getNameDish());
        Session session = sessionFactory.getCurrentSession();
        session.delete(preparedDish);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(PreparedDish item) {
        LOGGER.info("Status dish cooked!");
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
//        Query query = session.createQuery("update PreparedDish pd set pd.prepared = 1 where pd.id =:id");
//        query.setParameter("id", item.getId());
//        query.executeUpdate();
        session.flush();
        LOGGER.info("Status dish cooked prepared "+ item.getPrepared());
//        String query = "UPDATE PREPARED_DISH SET PREPARED_DISH = 1 WHERE id = ?";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PreparedDish findById(int id) {
        LOGGER.info("Find by id dish and cooked dish id = " + id);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pd from PreparedDish pd where id =:id");
        query.setParameter("id", id);
        return (PreparedDish) query.uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> findByName(String name) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> findAllDishThisOrder(int numberOrder) {
        LOGGER.info("Select all dishes in the order where id order: " + numberOrder);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pd from PreparedDish pd where pd.idOrder.id =:id");
        query.setParameter("id", numberOrder);
        return query.list();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAllPreparedCook(Cook cook) {
        LOGGER.info("Select all not prepared dishes");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pd from PreparedDish pd where pd.idOrder.closeOrOpenOrder = 1 and pd.prepared =:cook");
        query.setParameter("cook", cook);
        return query.list();
//        ORDER_WAITER.open_close = 1 AND PREPARED_DISH.prepared_dish = 0";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAllPrepared() {
        LOGGER.info("Select all prepared dishes");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pd from PreparedDish pd where pd.idOrder.closeOrOpenOrder = 1 and pd.prepared != null ");
        return query.list();
//        WHERE ORDER_WAITER.open_close = 1 AND PREPARED_DISH.prepared_dish = 1";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAllNotPrepared() {
        LOGGER.info("Select all not prepared dishes");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pd from PreparedDish pd where pd.idOrder.closeOrOpenOrder = 1 and pd.prepared is null");
        return query.list();
//        ORDER_WAITER.open_close = 1 AND PREPARED_DISH.prepared_dish = 0";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAllPreparedDishByOrder(int idOrder) {
        LOGGER.info("Delete all dish by order, where order: "+ idOrder);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from PreparedDish pd where pd.idOrder.id =:id");
        query.setParameter("id", idOrder);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

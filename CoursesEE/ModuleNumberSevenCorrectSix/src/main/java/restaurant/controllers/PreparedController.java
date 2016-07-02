package restaurant.controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.PreparedDish;
import restaurant.model.Hibernate.PreparedDishDao;

import java.util.List;

public class PreparedController implements MainMethodControllers<PreparedDish> {
    private DataSourceTransactionManager txManager;
    private PreparedDishDao preparedDishDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedController.class);
    private SessionFactory sessionFactory;

    public void setTxManager(DataSourceTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setPreparedDishDao(PreparedDishDao preparedDishDao) {
        this.preparedDishDao = preparedDishDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(PreparedDish item) {
        LOGGER.info("Add new prepared Dish in Database to order №" + item.getIdOrder());
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
//        preparedDishDao.createInPreparedDish(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAll() {
        LOGGER.info("Select all dishes prepared and not prepared");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select pd from PreparedDish pd").list();
//        return preparedDishDao.allInfoAboutPreparedDish();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(PreparedDish preparedDish) {
        LOGGER.info("Delete prepared dish by id: "+ preparedDish.getNameDish());
        Session session = sessionFactory.getCurrentSession();
        session.delete(preparedDish);
//        preparedDishDao.deletePreparedDish(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(PreparedDish item) {
        LOGGER.info("Status dish cooked!");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update PreparedDish pd set pd.prepared = 1 where pd.id =:id");
        query.setParameter("id", item.getId());
        query.executeUpdate();
        LOGGER.info("Status dish cooked prepared "+ item.getPrepared());
//        preparedDishDao.updateStatusCooked(item);
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
//        return preparedDishDao.findByIdPreparedDish(id);
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
//        return preparedDishDao.allDishesThisOrder(numberOrder);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAllPrepared() {
        LOGGER.info("Select all prepared dishes");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pd from PreparedDish pd where pd.idOrder.closeOrOpenOrder = 1 and pd.prepared = 1");
        return query.list();
//        WHERE ORDER_WAITER.open_close = 1 AND PREPARED_DISH.prepared_dish = 1";
//        return preparedDishDao.allPreparedDish();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAllNotPrepared() {
        LOGGER.info("Select all not prepared dishes");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select pd from PreparedDish pd where pd.idOrder.closeOrOpenOrder = 1 and pd.prepared = 0");
        return query.list();
//        ORDER_WAITER.open_close = 1 AND PREPARED_DISH.prepared_dish = 0";
//        return preparedDishDao.allNotPreparedDish();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAllPreparedDishByOrder(int idOrder) {
        LOGGER.info("Delete all dish by order, where order: "+ idOrder);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from PreparedDish pd where pd.idOrder.id =:id");
        query.setParameter("id", idOrder);

//        preparedDishDao.deleteAllDishesByOrder(idOrder);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

package restaurant.controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.OrderWaiter;

import java.util.List;


public class OrderController implements MainMethodControllers<OrderWaiter> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(OrderWaiter order) {
        LOGGER.info("Add new order in Database!");
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
//        orderWaiterDao.createOrder(order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> selectAll() {
        LOGGER.info("Select order in Database!");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select ow from OrderWaiter ow").list();
//        return orderWaiterDao.allInfoAboutOrderJOINUsers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(OrderWaiter id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from PreparedDish p where p.idOrder =:id");
        query.setParameter("id", id);
        query.executeUpdate();

//        preparedDishDao.deleteAllDishesByOrder(id);
        LOGGER.info("Delete all dishes with Database by order: "+ id);
        LOGGER.info("Delete order with Database where id: "+ id);
        Query queryOrder = session.createQuery("delete from OrderWaiter ow where ow.id =:id");
        queryOrder.setParameter("id", id.getId());
        queryOrder.executeUpdate();
//        orderWaiterDao.deleteOrder(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(OrderWaiter order) {
        LOGGER.info("Update order in Database!");
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
//        orderWaiterDao.updateOrder(order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderWaiter findById(int id) {
        LOGGER.info("Find order in Database of the id: "+ id);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ow from OrderWaiter ow where ow.id =:id");
        query.setParameter("id", id);
        return (OrderWaiter) query.uniqueResult();
//        return orderWaiterDao.findByIdOrder(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> findByName(String name) {
        return null;                                  // not method
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean closeOrder(int id) {
        LOGGER.info("Find order in Database and close order id: "+ id);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update OrderWaiter ow set ow.closeOrOpenOrder = 1 where ow.id =:id");
        query.setParameter("id", id);
        if(findById(id).getCloseOrOpenOrder() == 1){
            return true;
        } else {
            return false;
        }
//        return orderWaiterDao.closeOrder(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> selectAllOpenOrder() {
        LOGGER.info("Find all opened order with Database target = 0");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ow from OrderWaiter ow where ow.closeOrOpenOrder = 0");
        return query.list();
//        return orderWaiterDao.allOpenOrCloseOrder(0);
//        String query = "SELECT ORDER_WAITER.ID, ORDER_WAITER.id_user, ORDER_WAITER.ids_dishes, ORDER_WAITER.number_table, ORDER_WAITER.open_close, Users.first_name, users.last_name FROM  ORDER_WAITER INNER JOIN Users ON ORDER_WAITER.ID_USER = USERS.ID WHERE open_close = "+ target;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> selectAllCloseOrder() {
        LOGGER.info("Find all closed order with Database target = 1");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select ow from OrderWaiter ow where ow.closeOrOpenOrder = 1");
        return query.list();
//        return orderWaiterDao.allOpenOrCloseOrder(1);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

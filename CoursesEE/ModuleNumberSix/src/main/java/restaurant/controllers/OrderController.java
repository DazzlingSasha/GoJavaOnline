package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.*;

import java.util.List;


public class OrderController implements MainMethodControllers<OrderWaiter> {

    private DataSourceTransactionManager txManager;
    private OrderWaiterDao orderWaiterDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private PreparedDishDao preparedDishDao;

    public void setTxManager(DataSourceTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setOrderWaiterDao(OrderWaiterDao orderWaiterDao) {
        this.orderWaiterDao = orderWaiterDao;
    }

    public void setPreparedDishDao(PreparedDishDao preparedDishDao) {
        this.preparedDishDao = preparedDishDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(OrderWaiter order) {
        LOGGER.info("Add new order in Database!");
        orderWaiterDao.createOrder(order);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> selectAll() {
        LOGGER.info("Select order in Database!");
        return orderWaiterDao.allInfoAboutOrderJOINUsers();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(int id) {
        preparedDishDao.deleteAllDishesByOrder(id);
        LOGGER.info("Delete all dishes with Database by order: "+ id);
        LOGGER.info("Delete order with Database where id: "+ id);
        orderWaiterDao.deleteOrder(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(OrderWaiter order) {
        LOGGER.info("Update order in Database!");
        orderWaiterDao.updateOrder(order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderWaiter findById(int id) {
        LOGGER.info("Find order in Database of the id: "+ id);
        return orderWaiterDao.findByIdOrder(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> findByName(String name) {
        return null;                                  // not method
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean closeOrder(int id) {
        LOGGER.info("Find order in Database and close order id: "+ id);
        return orderWaiterDao.closeOrder(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> selectAllOpenOrder() {
        LOGGER.info("Find all opened order with Database target = 0");
        return orderWaiterDao.allOpenOrCloseOrder(0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<OrderWaiter> selectAllCloseOrder() {
        LOGGER.info("Find all closed order with Database target = 1");
        return orderWaiterDao.allOpenOrCloseOrder(1);
    }
}

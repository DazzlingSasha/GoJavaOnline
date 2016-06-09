package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.PreparedDish;
import restaurant.jdbc.database.PreparedDishDao;

import java.util.List;

public class PreparedController implements MainMethodControllers<PreparedDish> {
    private DataSourceTransactionManager txManager;
    private PreparedDishDao preparedDishDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedController.class);

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
        preparedDishDao.createInPreparedDish(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAll() {
        LOGGER.info("Select all dishes prepared and not prepared");
        return preparedDishDao.allInfoAboutPreparedDish();
    }

    @Override
    public void deleteWithDatabase(int id) {
        LOGGER.info("Delete prepared dish by id: "+ id);
        preparedDishDao.deletePreparedDish(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(PreparedDish item) {
        LOGGER.info("Status dish cooked!");
        preparedDishDao.updateStatusCooked(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PreparedDish findById(int id) {
        LOGGER.info("Find by id dish and cooked dish id = : " + id);
        return preparedDishDao.findByIdPreparedDish(id);
    }

    @Override
    public List<PreparedDish> findByName(String name) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> findAllDishThisOrder(int numberOrder) {
        LOGGER.info("Select all dishes in the order where id order: " + numberOrder);
        return preparedDishDao.allDishesThisOrder(numberOrder);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAllPrepared() {
        LOGGER.info("Select all prepared dishes");
        return preparedDishDao.allPreparedDish();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedDish> selectAllNotPrepared() {
        LOGGER.info("Select all not prepared dishes");
        return preparedDishDao.allNotPreparedDish();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAllPreparedDishByOrder(int idOrder) {
        LOGGER.info("Delete all dish by order, where order: "+ idOrder);
        preparedDishDao.deleteAllDishesByOrder(idOrder);
    }
}

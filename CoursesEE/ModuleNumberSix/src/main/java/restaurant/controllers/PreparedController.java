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
    public List<PreparedDish> selectAll() {
        return null;
    }

    @Override
    public void deleteWithDatabase(int id) {
    }

    @Override
    public void updateInDatabase(PreparedDish item) {
    }

    @Override
    public PreparedDish findById(int id) {
        return null;
    }

    @Override
    public List<PreparedDish> findByName(String name) {
        return null;
    }

    public List<PreparedDish> findAllDishThisOrder(int numberOrder) {
        return preparedDishDao.allDishesThisOrder(numberOrder);
    }
}

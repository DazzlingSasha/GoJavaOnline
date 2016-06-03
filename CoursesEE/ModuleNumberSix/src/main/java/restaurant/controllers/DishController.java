package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.DishDao;

import java.util.List;

public class DishController implements MainMethodControllers<Dish> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DishController.class);
    private PlatformTransactionManager txManager;

    private DishDao dishDao;

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }


    @Override
    public void addInDatabase(Dish dish) {
        LOGGER.info("Add new user!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> selectAll() {
        LOGGER.info("Add new user!");
        return dishDao.allInfoAboutDishes();
    }

    @Override
    public void deleteWithDatabase(int id) {
        LOGGER.info("Add new user!");
    }

    @Override
    public void updateInDatabase(Dish dish) {
        LOGGER.info("Add new user!");
    }

    @Override
    public Dish findById(int id) {
        return null;
    }

    @Override
    public List<Dish> findByName(String name) {
        return null;
    }

}

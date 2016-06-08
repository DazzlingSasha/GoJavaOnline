package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.DishDao;
import restaurant.jdbc.database.DishIngredient;

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
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Dish dish) {
        LOGGER.info("Add new dish!");
        dishDao.createNewDish(dish);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> selectAll() {
        LOGGER.info("Select menu category and dish!");
        return dishDao.selectMenuJoinDish();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(int id) {
        LOGGER.info("Delete dish and all ingredients this dish!");
        dishDao.deleteAllIngredientsForDish(id);
        dishDao.deleteDish(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Dish dish) {
        LOGGER.info("Update dish!");
        dishDao.updateDish(dish);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Dish findById(int id) {
        LOGGER.info("Add new user!");
        return dishDao.findDishByIdJoinManu(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> findByName(String name) {
        LOGGER.info("Find by name dishes!");
        return dishDao.findDishesByNameMenuJoinDish(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void findAllDishAndDeleteCategory(int category) {
        LOGGER.info("Update all dishes with category: " + category + " on category NOT CATEGORY!");
        dishDao.updateAllDishCategoryOnNOTCATEGORY(category);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCategoryDish(int id) {
        LOGGER.info("Delete category in the dish with id: " + id);
        dishDao.deleteCategory(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setDishCategory(int id, int numberCategory) {
        LOGGER.info("Set category in the dish with id: " + id);
        dishDao.setDishCategory(id, numberCategory);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> selectAllDishOneCategory(int category) {
        LOGGER.info("Select all dish one category!");
        return dishDao.selectMenuJoinDishOneCategory(category);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<DishIngredient> selectAllIngredientsDish(int idDish) {
        LOGGER.info("Select all ingredients for dish !");
        return dishDao.selectIngredientsThisDish(idDish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDishIngredient(int idDish, int idIngredient, double quantity) {
        LOGGER.info("Add new ingredient to dish!");
        dishDao.addNewIngredientForDish(idDish, idIngredient, quantity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteIngredientsWithThisDish(int idIngredient) {
        LOGGER.info("Delete ingredient with dish!");
        dishDao.deleteIngredientForDish(idIngredient);
    }
}

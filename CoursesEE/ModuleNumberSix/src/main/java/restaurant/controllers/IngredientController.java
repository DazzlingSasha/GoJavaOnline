package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.jdbc.database.Ingredient;
import restaurant.jdbc.database.IngredientDao;
import restaurant.jdbc.database.Warehouse;

import java.util.List;

public class IngredientController implements MainMethodControllers<Ingredient>{
    private DataSourceTransactionManager txManager;
    private IngredientDao ingredientDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientController.class);

    public void setTxManager(DataSourceTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Ingredient item) {
        LOGGER.info("Add new Ingredient!");
        ingredientDao.createInIngredients(item.getName());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ingredient> selectAll() {
        LOGGER.info("Select all Ingredients!");
        return ingredientDao.allInfoAboutIngredients();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(int id) {
        LOGGER.info("Delete ingredients by id: "+ id);
        ingredientDao.deleteIngredient(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Ingredient item) {
        LOGGER.info("Update ingredients! ");
        ingredientDao.updateInIngredients(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Ingredient findById(int id) {
        LOGGER.info("Select ingredients by id: "+ id);
        return ingredientDao.loadOneIngredient(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ingredient> findByName(String name) {
        LOGGER.info("Select ingredients by name: "+ name);
        return ingredientDao.findByName(name);
    }
}

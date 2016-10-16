package restaurant.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.DishDao;
import restaurant.dao.IngredientDao;
import restaurant.model.Dish;
import restaurant.model.Ingredient;

import java.util.List;

public class IngredientService {

    private IngredientDao ingredientDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ingredient> getIngredient(){
        return ingredientDao.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Ingredient getById(int id) {
        return ingredientDao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createIngredient(Ingredient ingredient) {
        ingredientDao.addInDatabase(ingredient);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeIngredient(Ingredient ingredient) {
        ingredientDao.deleteWithDatabase(ingredient);
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}

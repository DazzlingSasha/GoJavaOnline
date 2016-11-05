package restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.DishDao;
import restaurant.dao.IngredientDao;
import restaurant.dao.MenuDao;
import restaurant.model.Dish;
import restaurant.model.DishIngredient;
import restaurant.model.Menu;

import java.util.List;
@Service
public class DishService {

    private DishDao dishDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addDish(Dish dish){
        dishDao.addInDatabase(dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDish(Dish dish){
        dishDao.updateInDatabase(dish);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeDish(Dish dish){
        dishDao.deleteWithDatabase(dish);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> getDish(){
        return dishDao.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Dish getDishById(int id) {
        return dishDao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addIngredientForDish(DishIngredient dishIngredient) {
        dishDao.addInDishIngredient(dishIngredient);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteIngredientForDish(int id) {
        dishDao.deleteIngredientsWithThisDish(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<DishIngredient> getIngredientsByAllDishes() {
        return dishDao.getAllIngredientsForDishes();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DishIngredient getOneIngredientDish(int id) {
        return dishDao.findIngredient(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> getDishByName(String name){
        return dishDao.findByName(name);
    }


    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

}

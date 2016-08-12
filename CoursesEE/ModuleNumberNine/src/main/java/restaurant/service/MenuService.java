package restaurant.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.DishDao;
import restaurant.dao.IngredientDao;
import restaurant.dao.MenuDao;
import restaurant.model.Dish;
import restaurant.model.Menu;

import java.util.List;

public class MenuService {
    private MenuDao menuDao;
    private DishDao dishDao;
    private IngredientDao ingredientDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Menu> getMenu(){
        return menuDao.selectAll();
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
    public List<Dish> getDishByName(String name){
        return dishDao.findByName(name);
    }


    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}

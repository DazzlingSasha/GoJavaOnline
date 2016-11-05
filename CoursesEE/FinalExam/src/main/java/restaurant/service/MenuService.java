package restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.dao.DishDao;
import restaurant.dao.IngredientDao;
import restaurant.dao.MenuDao;
import restaurant.model.Dish;
import restaurant.model.Menu;

import java.util.List;
@Service
public class MenuService {
    private MenuDao menuDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Menu> getMenu(){
        return menuDao.selectAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addMenu(Menu menu){
        menuDao.addInDatabase(menu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMenu(Menu menu){
        menuDao.updateInDatabase(menu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeMenu(Menu menu){
        menuDao.deleteWithDatabase(menu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Menu getMenuById(int id){
       return menuDao.findById(id);
    }


    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

}

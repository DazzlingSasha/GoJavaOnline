package restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Menu;
import restaurant.model.Hibernate.MenuDao;

import java.util.List;

public class MenuController implements MainMethodControllers<Menu> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
    private DataSourceTransactionManager txManager;
    private MenuDao menuDao;

    public void setTxManager(DataSourceTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addInDatabase(Menu item) {
        LOGGER.info("Add new create category menu");
        menuDao.createdCategoryMenu(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Menu> selectAll() {
        LOGGER.info("Select all category menu");
        return menuDao.allInfoAboutMenu();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(Menu id) {
//        LOGGER.info("Delete menu category with id: " + id);
//        menuDao.deleteMenuCategory(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Menu item) {
        LOGGER.info("Update category menu with id: " + item.getId());
        menuDao.updateCategoryWithMenu(item);
    }

    @Override
    public Menu findById(int id) {
        return null;
    }

    @Override
    public List<Menu> findByName(String name) {
        return null;
    }

}

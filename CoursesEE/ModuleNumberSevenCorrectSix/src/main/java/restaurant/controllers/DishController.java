package restaurant.controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Dish;
import restaurant.model.Hibernate.DishDao;
import restaurant.model.DishIngredient;

import java.util.List;

public class DishController implements MainMethodControllers<Dish> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DishController.class);
    private PlatformTransactionManager txManager;

    private DishDao dishDao;
    private SessionFactory sessionFactory;

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
        Session session = sessionFactory.getCurrentSession();
        session.save(dish);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> selectAll() {
        LOGGER.info("Select menu category and dish!");
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Dish d").list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWithDatabase(Dish dish) {
        LOGGER.info("Delete dish and all ingredients this dish!" + dish.getName());
        Session session = sessionFactory.getCurrentSession();
        session.delete(dish);
//        dishDao.deleteAllIngredientsForDish(dish);
//        dishDao.deleteDish(dish);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateInDatabase(Dish dish) {
        LOGGER.info("Update dish!");
        Session session = sessionFactory.getCurrentSession();
        session.update(dish);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Dish findById(int id) {
        LOGGER.info("Find dish!");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.id =:id");
        query.setParameter("id", id);
        return (Dish) query.uniqueResult();
//        return dishDao.findDishByIdJoinManu(id);
//        String query = "SELECT DISH.id, DISH.NAME, DISH.id_category, DISH.COST, DISH.WEIGHT, MENU.name_category FROM DISH " +
//                "INNER JOIN MENU ON MENU.ID = dish.id_category WHERE DISH.id = " + id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> findByName(String name) {
        LOGGER.info("Find by name dishes!");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.name like :name");
        query.setParameter("name", "%"+name+"%");
        return query.list();
//        return dishDao.findDishesByNameMenuJoinDish(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void findAllDishAndDeleteCategory(int category) {
        LOGGER.info("Find all dishes with category and delete: " + category + " on category NOT CATEGORY!");
//        dishDao.updateAllDishCategoryOnNOTCATEGORY(category);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Dish d SET  d.category.id = 1 WHERE d.category.id =:category");
        query.setParameter("category", category);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCategoryDish(int id) {
        LOGGER.info("Delete category in the dish with id: " + id);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Dish d SET  d.category.id = 1 WHERE d.id =:id");
        query.setParameter("id", id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void setDishCategory(int id, int numberCategory) {
        LOGGER.info("Set category in the dish with id: " + id+ " and category id "+ numberCategory);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Dish d SET  d.category.id =:numberCategory WHERE d.id =:id");
        query.setParameter("numberCategory", numberCategory);
        query.setParameter("id", id);
//        dishDao.setDishCategory(id, numberCategory);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> selectAllDishOneCategory(int category) {
        LOGGER.info("Select all dish one category!");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.category.id =:category");
        query.setParameter("category", category);
        return query.list();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<DishIngredient> selectAllIngredientsDish(int idDish) {
        LOGGER.info("Select all ingredients for dish !");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.idDish =:idDish");
        query.setParameter("idDish", idDish);
        return query.list();
//        return dishDao.selectIngredientsThisDish(idDish);
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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

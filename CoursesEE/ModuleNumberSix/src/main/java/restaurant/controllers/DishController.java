package restaurant.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import restaurant.Main;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.DishDao;

import java.util.List;

public class DishController implements MainMethodController<Dish>{

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

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> selectAll() {
        return dishDao.allInfoAboutDishes();
    }

    @Override
    public void deleteWithDatabase(int id) {

    }

    @Override
    public void updateInDatabase(Dish dish) {

    }


//    public List<Dish> getAllDish() {
//        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
//        //PROPAGATION_REQUIRED - если транзакция еще не была создана, тогда создаст, а если была тогда будет использовать еще и дальше
//        //PROPAGATION_REQUIRED_NEW - создает новую транзакцию а остальные приостанавливает
//        //PROPAGATION_MANDATORY - до вызова getTransaction уже должен быть открытый или создана транзакция
//        try{
//            List<Dish> result = dishDao.allInfoAboutDishes();
//            txManager.commit(status);
//            return result;
//        } catch (Exception e){
//            txManager.rollback(status);
//            throw new RuntimeException(e);
//        }
//
//    }
}

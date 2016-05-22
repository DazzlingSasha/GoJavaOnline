package restaurant.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.DishDao;

import java.util.List;

public class DishController {

    private PlatformTransactionManager txManager;
    private DishDao dishDao;
    private ObservableList<Dish> usersData = FXCollections.observableArrayList();
    @FXML
    private TableView<Dish> tableUsers;

    @FXML
    private TableColumn<Dish, Integer> idColumn;

    @FXML
    private TableColumn<Dish, String> loginColumn;

    @FXML
    private TableColumn<Dish, String> passwordColumn;

    @FXML
    private TableColumn<Dish, Integer> emailColumn;

    @FXML
    private void initialize() {
//        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("ids_ingredients_dish"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("cost"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);
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
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Dish> getAllDish() {
        return dishDao.allInfoAboutDishes();
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void ActionDish2(ActionEvent actionEvent) {
        System.out.println("ssss");
    }

    public void ActionDish(ActionEvent actionEvent) {
        System.out.println("ddddd");
    }
}

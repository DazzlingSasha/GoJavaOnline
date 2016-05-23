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
    private ObservableList<Dish> dishData = FXCollections.observableArrayList();

    @FXML
    private TableView<Dish> tableDish;

    @FXML
    private TableColumn<Dish, Integer> idColumn;

    @FXML
    private TableColumn<Dish, String> nameColumn;

    @FXML
    private TableColumn<Dish, Integer> categoryColumn;

    @FXML
    private TableColumn<Dish, String> ingredientsForDishesColumn;

    @FXML
    private TableColumn<Dish, Integer> costColumn;

    @FXML
    private TableColumn<Dish, Integer> weightColumn;



    @FXML
    private void initialize() {
//        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("category"));
        ingredientsForDishesColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("ingredientsForDishes"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("cost"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("weight"));

        // заполняем таблицу данными
        tableDish.setItems(dishData);
    }
    private void initData() {
        List<Dish> list = getAllDish();
        System.out.println(list.size()+"//////////////////////////////////////////////////////////");
        for(int i = 0; i<list.size(); i++){
            System.out.println("----------------------------------------------" + i + " >> " + list.get(i));
            dishData.add(list.get(i));
        }
//        dishData.addAll(dishDao.allInfoAboutDishes().stream().collect(Collectors.toList()));
    }
        public List<Dish> getAllDish() {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
        //PROPAGATION_REQUIRED - если транзакция еще не была создана, тогда создаст, а если была тогда будет использовать еще и дальше
        //PROPAGATION_REQUIRED_NEW - создает новую транзакцию а остальные приостанавливает
        //PROPAGATION_MANDATORY - до вызова getTransaction уже должен быть открытый или создана транзакция
        try{
            List<Dish> result = dishDao.allInfoAboutDishes();
            txManager.commit(status);
            return result;
        } catch (Exception e){
            txManager.rollback(status);
            throw new RuntimeException(e);
        }

    }
//    @Transactional(propagation = Propagation.REQUIRED)
//    public List<Dish> getAllDish() {
//        return dishDao.allInfoAboutDishes();
//    }

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

//    @FXML
//    public void addContact() {
//        Contact contact = new Contact(txtName.getText(), txtPhone.getText(), txtEmail.getText());
//        contactService.save(contact);
//        data.add(contact);
//
//        // чистим поля
//        txtName.setText("");
//        txtPhone.setText("");
//        txtEmail.setText("");
//    }
}

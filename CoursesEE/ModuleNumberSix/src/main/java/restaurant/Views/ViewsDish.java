package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.Main;
import restaurant.jdbc.database.Dish;

import java.util.List;

public class ViewsDish {
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
        initData();

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
        List<Dish> list = Main.beanDishController();
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
            dishData.add(list.get(i));
        }
    }

    public Button butAdd;
    public Button butDelete;
    public Button butSearch;
    public Button butSelectAll;

    public void ActionDish(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(!(source instanceof Button)){
            return;
        }
        Dish selectDish = tableDish.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        switch (button.getId()){
            case "butAdd":
                System.out.println("ssss1" + selectDish);
                break;
            case "butDelete":
                System.out.println("ssss2");
                break;
            case "butSearch":
                System.out.println("ssss3" + selectDish);
                break;
            case "butSelectAll":
                System.out.println("ssss4");
                break;

        }
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

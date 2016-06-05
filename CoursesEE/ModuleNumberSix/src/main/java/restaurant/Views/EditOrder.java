package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.OrderWaiter;
import restaurant.jdbc.database.PreparedDish;
import restaurant.jdbc.database.Users;

public class EditOrder {
    private ObservableList<Dish> dishData = FXCollections.observableArrayList();
    private ObservableList<Dish> preparedData = FXCollections.observableArrayList();

    @FXML
    private TableView<Dish> tableDish;
    @FXML
    private TableColumn<Dish, Integer> idColumn;
    @FXML
    private TableColumn<Dish, String> nameColumn;
    @FXML
    private TableColumn<Dish, Integer> categoryColumn;

    @FXML
    private TableView<PreparedDish> tablePrepared;
    @FXML
    private TableColumn<PreparedDish, Integer> idPreparedColumn;
    @FXML
    private TableColumn<PreparedDish, String> namePreparedColumn;
    @FXML
    private TableColumn<PreparedDish, Integer> categoryPreparedColumn;

    @FXML
    private void initialize() {
        preparedData.addAll(Main.beanPreparedController().findAllDishThisOrder(order.getId()));

        dishData.addAll(Main.beanDishController());

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("category"));

        // заполняем таблицу данными
        tableDish.setItems(dishData);
    }


    @FXML
    public ComboBox<Users> userColumn;
    @FXML
    public TextField tableColumn;

    private Stage dialogStage;
    private OrderWaiter order;
    private boolean okClicked = false;
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOrder(OrderWaiter order) {
        this.order = order;
        addInComboBox();

        userColumn.setValue(Main.beanUserController().findById(order.getId_user()));
        tableColumn.setText(Integer.toString(order.getNumberTable()));
    }
    private void addInComboBox() {
        for (Users item : Main.beanUserController().allUsersWaiter()) {
            userColumn.getItems().add(item);
        }
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            order.setId_user(userColumn.getSelectionModel().getSelectedItem().getId());
            order.setNumberTable(Integer.parseInt(tableColumn.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(tableColumn, "table"));
        if (userColumn.getSelectionModel().getSelectedItem() == null) {
            errorMessage.append("No valid ComboBox waiter tables!\n");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }

    public void ActionAdd(ActionEvent actionEvent) {

    }

    public void ActionDelete(ActionEvent actionEvent) {
    }
}

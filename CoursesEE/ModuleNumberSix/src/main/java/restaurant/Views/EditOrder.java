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
    private ObservableList<PreparedDish> preparedData = FXCollections.observableArrayList();

    @FXML
    private TableView<Dish> tableDish;
    @FXML
    private TableColumn<Dish, Integer> idColumn;
    @FXML
    private TableColumn<Dish, String> nameColumn;
    @FXML
    private TableColumn<Dish, Integer> categoryColumn;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private OrderWaiter order;
    private PreparedDish preparedDish;
    private boolean okClicked = false;
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private TableView<PreparedDish> tablePrepared;
    @FXML
    private TableColumn<PreparedDish, Integer> idPreparedColumn;
    @FXML
    private TableColumn<PreparedDish, String> namePreparedColumn;
    @FXML
    private TableColumn<PreparedDish, String> categoryPreparedColumn;

    public void setOrder(OrderWaiter order) {
        this.order = order;
        addInComboBox();
        userColumn.setValue(Main.beanUserController().findById(order.getId_user()));
        tableColumn.setText(Integer.toString(order.getNumberTable()));

        //-----------------------------first initialize the second table------------------------------------------------
        dishData.addAll(Main.beanDishController().selectAll());

        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("category"));

        tableDish.setItems(dishData);

        //-----------------------------second initialize the first table------------------------------------------------
        preparedData.addAll(Main.beanPreparedController().findAllDishThisOrder(order.getId()));
//        preparedData.addAll(Main.beanPreparedController().selectAll());
        idPreparedColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, Integer>("idDish"));
        namePreparedColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, String>("nameDish"));
        categoryPreparedColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, String>("categoryDish"));

        tablePrepared.setItems(preparedData);
    }

    private void addInComboBox() {
        for (Users item : Main.beanUserController().allUsersWaiter()) {
            userColumn.getItems().add(item);
        }
    }

    @FXML
    public ComboBox<Users> userColumn;

    @FXML
    public TextField tableColumn;

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
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
//        if (preparedData.size() == 0) {
//            errorMessage.append("No valid add one more dish!\n");
//        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }

    public void ActionAdd(ActionEvent actionEvent) {
        if (order.getId() == 0) {
            isInputValid();
            order.setId_user(userColumn.getSelectionModel().getSelectedItem().getId());
            order.setNumberTable(Integer.parseInt(tableColumn.getText()));
            order.setIdsDishes(Integer.toString(preparedData.size()));
            Main.beanOrderController().addInDatabase(order);
            System.out.println(order.getId());
        }
        PreparedDish addInDishToOrder = new PreparedDish();
        addInDishToOrder.setIdDish(tableDish.getSelectionModel().getSelectedItem().getId());
        addInDishToOrder.setIdOrder(order.getId());
        addInDishToOrder.setIdUser(userColumn.getSelectionModel().getSelectedItem().getId());
        Main.beanPreparedController().addInDatabase(addInDishToOrder);
        preparedData.add(addInDishToOrder);
        tablePrepared.setItems(preparedData);
    }

    public void ActionDelete(ActionEvent actionEvent) {
        int selectedIndex = tablePrepared.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tablePrepared.getItems().remove(selectedIndex);
            Main.beanPreparedController().deleteWithDatabase(tablePrepared.getSelectionModel().getSelectedItem().getId());
        } else {// Ничего не выбрано.
            alertAndErrorMessages.unspecifiedDialog();
        }
    }
}

package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.model.Dish;
import restaurant.model.OrderWaiter;
import restaurant.model.PreparedDish;
import restaurant.model.Users;

import java.util.ArrayList;
import java.util.List;

public class AddOrder {
    private ObservableList<Dish> dishData = FXCollections.observableArrayList();
    private ObservableList<PreparedDish> preparedData = FXCollections.observableArrayList();

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
//        userColumn.setValue("Select users");
        tableColumn.setPromptText("Enter new Table");
    }

    @FXML
    private void initialize() {
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
            List<Dish> list = new ArrayList<>();
            order.setId_user(userColumn.getSelectionModel().getSelectedItem());
            order.setNumberTable(Integer.parseInt(tableColumn.getText()));
            order.setIdsDishes(list.toString());
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

}

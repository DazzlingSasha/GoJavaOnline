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

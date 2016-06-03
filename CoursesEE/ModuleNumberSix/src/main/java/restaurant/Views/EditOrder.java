package restaurant.Views;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.jdbc.database.OrderWaiter;


public class EditOrder {

    @FXML
    public ComboBox userColumn;
    @FXML
    public TextField tableColumn;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private OrderWaiter order;
    private boolean okClicked = false;
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOrder(OrderWaiter order) {
        this.order = order;
        tableColumn.setText(Integer.toString(order.getNumberTable()));
        userColumn.setValue(Main.beanUserController().selectAll());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
//            order.setName(nameField.getText());
//            order.setCategory(Integer.parseInt(idCategoryField.getText()));
//            order.setIngredientsForDishes(idsIngredientsDishField.getText());
//            order.setCost(Integer.parseInt(costField.getText()));
//            order.setWeight(Integer.parseInt(weightField.getText()));

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

//        errorMessage.append(alertAndErrorMessages.validStringField(nameField, "name dish"));
//        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(idCategoryField, "id category"));
//        errorMessage.append(alertAndErrorMessages.validStringField(idsIngredientsDishField, "idsIngredientsDishField"));
//        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(costField, "cost"));
//        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(weightField, "weight"));

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }
}

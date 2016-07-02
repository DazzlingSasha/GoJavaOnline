package restaurant.Views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.model.Ingredient;

public class EditIngredient {

    @FXML
    public TextField nameField;
    @FXML
    public TextField unit;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private Ingredient item;
    private boolean okClicked = false;

    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUser(Ingredient item) {
        this.item = item;
        nameField.setText(item.getName());
        unit.setText(item.getUnit());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            item.setName(nameField.getText());
            item.setUnit(unit.getText());
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
        errorMessage.append(alertAndErrorMessages.validStringField(nameField, "first name"));
        errorMessage.append(alertAndErrorMessages.validStringField(unit, "unit"));

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }
}

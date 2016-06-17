package restaurant.Views;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.model.Ingredient;
import restaurant.model.Warehouse;

public class EditWarehouse{

    @FXML
    public ComboBox<Ingredient> idIngredientColumn;
    @FXML
    public TextField quantityColumn;
    @FXML
    public TextField unitColumn;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private Warehouse warehouse;
    private boolean okClicked = false;
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUser(Warehouse warehouse) {
        this.warehouse = warehouse;
        addInComboBox();
        idIngredientColumn.setPromptText("New item");
        idIngredientColumn.setValue(Main.beanIngredientController().findById(warehouse.getIdIngredient().getId()));
        quantityColumn.setText(Double.toString(warehouse.getQuantity()));
        unitColumn.setText(warehouse.getUnit());
    }

    private void addInComboBox() {
        for (Ingredient item : Main.beanIngredientController().selectAll()) {
            idIngredientColumn.getItems().add(item);
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            warehouse.setIdIngredient(idIngredientColumn.getSelectionModel().getSelectedItem());
            warehouse.setItemWithDatabaseIngredients(idIngredientColumn.getSelectionModel().getSelectedItem().getName());
            warehouse.setQuantity(Double.parseDouble(quantityColumn.getText()));
            warehouse.setUnit(unitColumn.getText());

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

        if (idIngredientColumn.getSelectionModel().getSelectedItem() == null) {
            errorMessage.append("No valid ComboBox Ingredient!\n");
        }
        errorMessage.append(alertAndErrorMessages.validDoubleAndNull(quantityColumn, "quantity"));
        errorMessage.append(alertAndErrorMessages.validStringField(unitColumn, "unit"));

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }
}

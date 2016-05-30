package restaurant.Views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.DateSQL;
import restaurant.jdbc.database.Users;
import restaurant.jdbc.database.Warehouse;

public class EditWarehouse {
    @FXML
    public TextField idIngredientColumn;
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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUser(Warehouse warehouse) {
        this.warehouse = warehouse;
        idIngredientColumn.setText(Integer.toString(warehouse.getIdIngredient()));
        quantityColumn.setText(Double.toString(warehouse.getQuantity()));
        unitColumn.setText(warehouse.getUnit());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            warehouse.setIdIngredient(Integer.parseInt(idIngredientColumn.getText()));
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

        if (idIngredientColumn.getText() == null || idIngredientColumn.getText().length() == 0) {
            errorMessage.append("No valid salary!\n");
        } else {
            // пытаемся преобразовать почтовый код в int.
            try {
                Integer.parseInt(idIngredientColumn.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("No valid salary (must be an integer)!\n");
            }
        }
        if (quantityColumn.getText() == null || quantityColumn.getText().length() == 0) {
            errorMessage.append("No valid salary!\n");
        } else {
            // пытаемся преобразовать почтовый код в int.
            try {
                Double.parseDouble(quantityColumn.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("No valid salary (must be an integer)!\n");
            }
        }
        if (unitColumn.getText() == null || unitColumn.getText().length() == 0) {
            errorMessage.append("No valid phone!\n");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage.toString());

            alert.showAndWait();

            return false;
        }
    }
}

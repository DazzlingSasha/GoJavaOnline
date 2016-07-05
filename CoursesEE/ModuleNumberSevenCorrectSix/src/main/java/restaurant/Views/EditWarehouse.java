package restaurant.Views;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.model.Ingredient;
import restaurant.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class EditWarehouse{

    @FXML
    public ComboBox<Ingredient> idIngredientColumn;
    @FXML
    public TextField quantityColumn;

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
        idIngredientColumn.setPromptText("New item");

        if(warehouse.getIdIngredient() == null) {
            addInComboBox();
        } else {
            idIngredientColumn.setValue(warehouse.getIdIngredient());
        }

        quantityColumn.setText(Double.toString(warehouse.getQuantity()));

    }

    private void addInComboBox() {
        List<Ingredient> arrIngredients = new ArrayList<>();
        arrIngredients.addAll(Main.beanIngredientController().selectAll());
        List<Warehouse> arrWarehouse = new ArrayList<>();
        arrWarehouse.addAll(Main.beanWarehouseController().selectAll());

        for (int k = 0; k < arrWarehouse.size(); k++) {
            for(int i = 0; i < arrIngredients.size(); i++){
                if(arrIngredients.get(i).equals(arrWarehouse.get(k).getIdIngredient())){
                    arrIngredients.remove(i);
                    break;
                }
            }
        }

        idIngredientColumn.getItems().addAll(arrIngredients);
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


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }
}

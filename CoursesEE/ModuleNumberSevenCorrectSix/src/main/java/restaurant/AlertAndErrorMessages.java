package restaurant;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.model.Warehouse;

public class AlertAndErrorMessages {

    public void dialogFields(Stage dialogStage, String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    public void dialogNotAllIngredients(Stage dialogStage, String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Add Ingredients on the Warehouse");
        alert.setHeaderText("The Warehouse is not necessary ingredients:");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }
    public void dialogEndsWarehouse(Stage dialogStage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Warehouse");
        alert.setHeaderText("All Goods is ending < 10");

        StringBuilder sb = new StringBuilder();
        for(Warehouse warehouse : Main.beanWarehouseController().findEndsItemsInWarehouse()){
            sb.append(warehouse.getIdIngredient().getName()).append(" quantity = ").append(warehouse.getQuantity()).append("\n");
        }
        alert.setContentText(sb.toString());

        alert.showAndWait();
    }

    public void unspecifiedDialog() {
        // Ничего не выбрано.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No User Selected");
        alert.setContentText("Please select a user in the table");

        alert.showAndWait();
    }
    public void selectOtherCategoryDialog() {
        // Ничего не выбрано.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Not Category");
        alert.setHeaderText("Is not select this category");
        alert.setContentText("Please select a other category");

        alert.showAndWait();
    }
    public void unspecifiedDialogOrderClose() {
        // Ничего не выбрано.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Not delete order");
        alert.setHeaderText("Is not delete or edit selecting order but order is closing");
        alert.setContentText("Please select a order with value open = 0");

        alert.showAndWait();
    }

    public String validIntegerAndNull(TextField field, String nameField){
        if (field.getText() == null || field.getText().length() == 0) {
            return "No valid "+nameField+"\n";
        } else {
            // пытаемся преобразовать почтовый код в int.
            try {
                Integer.parseInt(field.getText());
                return "";
            } catch (NumberFormatException e) {
                return "No valid "+nameField+" (must be an integer)!\n";
            }
        }
    }

    public String validDoubleAndNull(TextField field, String nameField){
        if (field.getText() == null || field.getText().length() == 0) {
            return "No valid "+nameField+"\n";
        } else {
            // пытаемся преобразовать почтовый код в double.
            try {
                Double.parseDouble(field.getText());
                return "";
            } catch (NumberFormatException e) {
                return "No valid "+nameField+" (must be an double)!\n";
            }
        }
    }

    public String validStringField(TextField field, String nameField){
        return (field.getText() == null || field.getText().length() == 0)
                ? "No valid last "+nameField+"!\n" : "";
    }
}

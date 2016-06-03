package restaurant;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlertAndErrorMessages {

    public void dialogFields(Stage dialogStage, String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }

    public void unspecifiedDialog() {
        // Ничего не выбрано.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No User Selected");
        alert.setContentText("Please select a user in the table.");

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

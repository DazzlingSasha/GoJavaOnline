package restaurant.Views;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.DateSQL;
import restaurant.model.Position;
import restaurant.model.Users;

public class EditUser {
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public TextField birthdayField;
    @FXML
    public TextField phoneField;
    @FXML
    public ComboBox<Position> positionUserField;
//    @FXML
//    public TextField positionUserField;
    @FXML
    public TextField salaryField;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private Users user;
    private boolean okClicked = false;
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    Position position[] = Position.values();

    public void setUser(Users user) {
        this.user = user;

        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());

        birthdayField.setText(DateSQL.format(user.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");

        phoneField.setText(user.getPhone());
        positionUserField.getItems().addAll(Position.values());
        positionUserField.setPromptText("Position");
//        positionUserField.setItems(position[user.getPositionUser().ordinal()].toString());

        salaryField.setText(Integer.toString(user.getSalary()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setBirthday(DateSQL.parse(birthdayField.getText()));
            user.setPhone(phoneField.getText());
            user.setPositionUser(positionUserField.getSelectionModel().getSelectedItem());
            user.setSalary(Integer.parseInt(salaryField.getText()));

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
        errorMessage.append(alertAndErrorMessages.validStringField(firstNameField, "first name"));
        errorMessage.append(alertAndErrorMessages.validStringField(lastNameField, "last name"));

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage.append("No valid birthday!\n");
        } else {
            if (!DateSQL.validDate(birthdayField.getText())) {
                errorMessage.append("No valid birthday. Use the format dd.mm.yyyy!\n");
            }
        }

        errorMessage.append(alertAndErrorMessages.validStringField(phoneField, "phone"));
//        errorMessage.append(alertAndErrorMessages.validStringField(positionUserField, "position user"));
        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(salaryField, "salary"));

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }
}

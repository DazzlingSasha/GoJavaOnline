package restaurant.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.DateSQL;
import restaurant.jdbc.database.Users;

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
    public TextField positionUserField;
    @FXML
    public TextField salaryField;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private Users user;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUser(Users user) {
        this.user = user;

        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());

        birthdayField.setText(DateSQL.format(user.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");

        phoneField.setText(user.getPhone());
        positionUserField.setText(user.getPositionUser());
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
            user.setPositionUser(positionUserField.getText());
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

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage.append("No valid first name!\n");
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage.append("No valid last name!\n");
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage.append("No valid birthday!\n");
        } else {
            if (!DateSQL.validDate(birthdayField.getText())) {
                errorMessage.append("No valid birthday. Use the format dd.mm.yyyy!\n");
            }
        }
        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage.append("No valid phone!\n");
        }


        if (positionUserField.getText() == null || positionUserField.getText().length() == 0) {
            errorMessage.append("No valid position user!\n");
        }

        if (salaryField.getText() == null || salaryField.getText().length() == 0) {
            errorMessage.append("No valid salary!\n");
        } else {
            // пытаемся преобразовать почтовый код в int.
            try {
                Integer.parseInt(salaryField.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("No valid salary (must be an integer)!\n");
            }
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

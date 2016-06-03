package restaurant.Views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.jdbc.database.Dish;


public class EditDish {
    @FXML
    public TextField nameField;
    @FXML
    public TextField idCategoryField;
    @FXML
    public TextField idsIngredientsDishField;
    @FXML
    public TextField costField;
    @FXML
    public TextField weightField;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private Dish dish;
    private boolean okClicked = false;
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
        nameField.setText(dish.getName());
        idCategoryField.setText(Integer.toString(dish.getCategory()));
        idsIngredientsDishField.setText(dish.getIngredientsForDishes());
        costField.setText(Integer.toString(dish.getCost()));
        weightField.setText(Integer.toString(dish.getWeight()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            dish.setName(nameField.getText());
            dish.setCategory(Integer.parseInt(idCategoryField.getText()));
            dish.setIngredientsForDishes(idsIngredientsDishField.getText());
            dish.setCost(Integer.parseInt(costField.getText()));
            dish.setWeight(Integer.parseInt(weightField.getText()));

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

        errorMessage.append(alertAndErrorMessages.validStringField(nameField, "name dish"));
        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(idCategoryField, "id category"));
        errorMessage.append(alertAndErrorMessages.validStringField(idsIngredientsDishField, "idsIngredientsDishField"));
        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(costField, "cost"));
        errorMessage.append(alertAndErrorMessages.validIntegerAndNull(weightField, "weight"));

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }
}

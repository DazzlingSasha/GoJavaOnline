package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.DishIngredient;
import restaurant.jdbc.database.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class ViewsIngredientsForDish {
    private ObservableList<DishIngredient> dishIngredientData = FXCollections.observableArrayList();
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();
    private Dish dish;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public Label nameDish;
    @FXML
    public TableView<DishIngredient> ingredientTableView;
    @FXML
    public TableColumn<DishIngredient, Integer> idColumn;
    @FXML
    public TableColumn<DishIngredient, String> nameIngredientColumn;
    @FXML
    public TableColumn<DishIngredient, Double> quantityColumn;
    @FXML
    public ComboBox<Ingredient> butSelectIngredients;

    @FXML
    private void initialize() {}

    public void setDish(Dish dish) {
        this.dish = dish;
        nameDish.setText(dish.getId() + " " + dish.getName());

        butSelectIngredients.getItems().addAll(Main.beanIngredientController().selectAll());
        dishIngredientData.addAll(Main.beanDishController().selectAllIngredientsDish(dish.getId()));

        idColumn.setCellValueFactory(new PropertyValueFactory<DishIngredient, Integer>("idDishIngredient"));
        nameIngredientColumn.setCellValueFactory(new PropertyValueFactory<DishIngredient, String>("nameIngredient"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<DishIngredient, Double>("quantity"));

        ingredientTableView.setItems(dishIngredientData);
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append(alertAndErrorMessages.validDoubleAndNull(nameField, "quantity ingredient"));

        if (butSelectIngredients.getSelectionModel().getSelectedItem() == null) {
            errorMessage.append("No valid ComboBox waiter tables!\n");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            alertAndErrorMessages.dialogFields(dialogStage, errorMessage.toString());
            return false;
        }
    }

    @FXML
    public TextField nameField;

    @FXML
    public Button butAdd;

    @FXML
    public Button butDelete;

    public void ActionIngredient(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }
        Ingredient selectDish = butSelectIngredients.getSelectionModel().getSelectedItem();
        int selectedIndex = ingredientTableView.getSelectionModel().getFocusedIndex();
        Button button = (Button) source;
        switch (button.getId()) {
            case "butAdd":
                if (isInputValid()) {
                    Main.beanDishController().addInDishIngredient(dish.getId(), selectDish.getId(), Double.parseDouble(nameField.getText()));
                    dishIngredientData.clear();
                    dishIngredientData.addAll(Main.beanDishController().selectAllIngredientsDish(dish.getId()));
                    ingredientTableView.setItems(dishIngredientData);
                } else {
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;


            case "butDelete":
                if (selectedIndex >= 0) {
                    ingredientTableView.getItems().remove(selectedIndex);
                    Main.beanDishController().deleteIngredientsWithThisDish(selectDish.getId());
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;
        }

    }
}
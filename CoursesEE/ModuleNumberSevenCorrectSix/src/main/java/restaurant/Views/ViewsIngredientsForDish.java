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
import restaurant.model.Dish;
import restaurant.model.DishIngredient;
import restaurant.model.Ingredient;


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
//        dishIngredientData.addAll(Main.beanDishController().selectAllIngredientsDish(dish.getId()));
        dishIngredientData.addAll(Main.beanDishController().selectAllIngredientsDish(dish.getId()));
        idColumn.setCellValueFactory(new PropertyValueFactory<DishIngredient, Integer>("idDish"));
        nameIngredientColumn.setCellValueFactory(new PropertyValueFactory<DishIngredient, String>("idIngredient"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<DishIngredient, Double>("quantity"));

        ingredientTableView.setItems(dishIngredientData);
    }

    private boolean okClicked = false;

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        okClicked = true;
        dialogStage.close();
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
                    DishIngredient dishIngredient = new DishIngredient(dish.getId(), selectDish, Integer.parseInt(nameField.getText()));

                    DishIngredient ingredient = Main.beanDishController().findInDishIngredient(dishIngredient);
                    if (ingredient == null){
                        Main.beanDishController().addInDishIngredient(dishIngredient);
                        dishIngredientData.add(dishIngredient);
                    } else {
                        ingredient.setQuantity(dishIngredient.getQuantity() + ingredient.getQuantity());
                        Main.beanDishController().updateInDishIngredient(ingredient);
                        for(int i = 0; i < dishIngredientData.size(); i++){
                            if(dishIngredientData.get(i).getIdIngredient().getId() == ingredient.getIdIngredient().getId()){
                                dishIngredientData.set(i, ingredient);
                                break;
                            }
                        }
                    }

                    ingredientTableView.setItems(dishIngredientData);
                } else {
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;


            case "butDelete":
                if (selectedIndex >= 0) {
                    ingredientTableView.getItems().remove(selectedIndex);
                    Main.beanDishController().deleteIngredientsWithThisDish(ingredientTableView.getSelectionModel().getSelectedItem());
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;
        }

    }

}
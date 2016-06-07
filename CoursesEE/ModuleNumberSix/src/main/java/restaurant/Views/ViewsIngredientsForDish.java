package restaurant.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class ViewsIngredientsForDish {
    @FXML
    public TableView ingredientTableView;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn nameIngredientColumn;
    @FXML
    public TableColumn quantityColumn;


    @FXML
    public Button butAdd;
    @FXML
    public Button butDelete;
    @FXML
    public ComboBox butSelectIngredients;
    @FXML
    public TextField nameField;

    public void handleOk(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
    }

    public void ActionIngredient(ActionEvent actionEvent) {
    }
}

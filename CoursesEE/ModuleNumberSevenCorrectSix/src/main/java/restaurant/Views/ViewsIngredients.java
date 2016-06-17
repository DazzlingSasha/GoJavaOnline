package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.model.Ingredient;

import java.io.IOException;
import java.util.List;

public class ViewsIngredients {
    private ObservableList<Ingredient> ingredientData = FXCollections.observableArrayList();
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    @FXML
    private TableView<Ingredient> ingredientTableView;
    @FXML
    public TableColumn<Ingredient, Integer> idColumn;
    @FXML
    public TableColumn<Ingredient, String> nameIngredientColumn;

    @FXML
    private void initialize() {
        ingredientData.addAll(Main.beanIngredientController().selectAll());
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, Integer>("id"));
        nameIngredientColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
        // заполняем таблицу данными
        ingredientTableView.setItems(ingredientData);
    }

    @FXML
    public Button butAdd;
    @FXML
    public Button butDelete;
    @FXML
    public Button butEdit;
    @FXML
    public Button butSelectAll;
    @FXML
    public Button butSearch;

    @FXML
    public TextField textSearch;
    public void ActionIngredient(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }
        Button button = (Button) source;

        Ingredient selectIngredient = ingredientTableView.getSelectionModel().getSelectedItem();
        int selectedIndex = ingredientTableView.getSelectionModel().getSelectedIndex();

        switch (button.getId()) {
            case "butAdd":
                handleNewUser(actionEvent);
                break;

            case "butDelete":
                if (selectedIndex >= 0) {
                    ingredientTableView.getItems().remove(selectedIndex);
                    Main.beanIngredientController().deleteWithDatabase(selectIngredient);
                } else {
                    // Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butEdit":
                handleEditUser(actionEvent);
                ingredientData.set(selectedIndex, Main.beanIngredientController().findById(selectIngredient.getId()));
                ingredientTableView.setItems(ingredientData);
                break;

            case "butSelectAll":
                ingredientData.clear();
                ingredientData.addAll(Main.beanIngredientController().selectAll());
                ingredientTableView.setItems(ingredientData);
                break;

            case "butSearch":
                ingredientData.clear();
                System.out.println(textSearch.getText().toLowerCase());
                List<Ingredient> searchList = Main.beanIngredientController().findByName(textSearch.getText().toLowerCase());
                ingredientData.addAll(searchList);
                ingredientTableView.setItems(ingredientData);
                break;
        }
    }
    @FXML
    private void handleNewUser(ActionEvent actionEvent) {
        Ingredient ingredient = new Ingredient();
        boolean okClicked = showWarehouseEditDialog(actionEvent, ingredient);
        System.out.println(okClicked);
        if (okClicked) {
            Main.beanIngredientController().addInDatabase(ingredient);
            ingredientData.add(ingredient);
            ingredientTableView.setItems(ingredientData);
        }
    }

    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        Ingredient selectedItem = ingredientTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            boolean okClicked = showWarehouseEditDialog(actionEvent, selectedItem);
            if (okClicked) {
                System.out.println(selectedItem);
                Main.beanIngredientController().updateInDatabase(selectedItem);
            }
            System.out.println(okClicked);
        } else {
            alertAndErrorMessages.unspecifiedDialog();
        }
    }

    private boolean showWarehouseEditDialog(ActionEvent actionEvent, Ingredient item) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/ingredientEditAndAddDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit Ingridient");

            EditIngredient controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(item);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

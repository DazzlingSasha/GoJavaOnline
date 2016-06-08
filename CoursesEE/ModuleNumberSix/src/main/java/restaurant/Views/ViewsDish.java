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
import restaurant.controllers.MainMenuController;
import restaurant.jdbc.database.Dish;

import java.io.IOException;

public class ViewsDish {


    private ObservableList<Dish> dishData = FXCollections.observableArrayList();
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    @FXML
    private TableView<Dish> tableDish;

    @FXML
    private TableColumn<Dish, Integer> idColumn;

    @FXML
    private TableColumn<Dish, String> nameColumn;

    @FXML
    private TableColumn<Dish, String> categoryColumn;

    @FXML
    private TableColumn<Dish, String> ingredientsForDishesColumn;

    @FXML
    private TableColumn<Dish, Integer> costColumn;

    @FXML
    private TableColumn<Dish, Integer> weightColumn;

    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("nameCategory"));
        ingredientsForDishesColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("ingredientsForDishes"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("cost"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("weight"));

        // заполняем таблицу данными
        tableDish.setItems(dishData);
    }

    private void initData() {
        dishData.addAll(Main.beanDishController().selectAll());
    }

    @FXML
    public Button butAdd;
    @FXML
    public Button butEdit;
    @FXML
    public Button butDelete;
    @FXML
    public Button butSearch;
    @FXML
    public Button butSelectAll;
    @FXML
    public TextField textSearch;

    public void ActionDish(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }
        Dish selectDish = tableDish.getSelectionModel().getSelectedItem();
        int selectedIndex = tableDish.getSelectionModel().getFocusedIndex();
        Button button = (Button) source;
        switch (button.getId()) {
            case "butAdd":
                handleNewUser(actionEvent);
                break;

            case "butEdit":
                handleEditUser(actionEvent);
                dishData.set(selectedIndex, Main.beanDishController().findById(selectDish.getId()));
                tableDish.setItems(dishData);
                break;

            case "butDelete":
                if (selectedIndex >= 0) {
                    tableDish.getItems().remove(selectedIndex);
                    Main.beanDishController().deleteWithDatabase(selectDish.getId());
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butSearch":
                dishData.clear();
                dishData.addAll(Main.beanDishController().findByName(textSearch.getText().toLowerCase()));
                tableDish.setItems(dishData);
                break;

            case "butSelectAll":
                dishData.clear();
                dishData.addAll(Main.beanDishController().selectAll());
                tableDish.setItems(dishData);
                break;
        }
    }

    @FXML
    public Button butAddIngredients;

    public void ActionAddIngredientsDish(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }
        Dish selectDish = tableDish.getSelectionModel().getSelectedItem();
        if(selectDish != null) {

            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/viewsAddIngredientsInDish.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Add or Edit Ingredients for Dish");

            ViewsIngredientsForDish controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDish(selectDish);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
        } else {
            alertAndErrorMessages.unspecifiedDialog();
        }
//        new MainMenuController().newStage(actionEvent, "/views/viewsAddIngredientsInDish.fxml", "Add or Edit Ingredients for Dish");
    }

    @FXML
    private void handleNewUser(ActionEvent actionEvent) {
        Dish dish = new Dish();
        boolean okClicked = showPersonEditDialog(actionEvent, dish);
        System.out.println(okClicked);
        if (okClicked) {
            Main.beanDishController().addInDatabase(dish);
            tableDish.getItems().add(dish);
        }
    }

    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        Dish selectedMenu = tableDish.getSelectionModel().getSelectedItem();
        if (selectedMenu != null) {
            boolean okClicked = showPersonEditDialog(actionEvent, selectedMenu);
            if (okClicked) {
                Main.beanDishController().updateInDatabase(selectedMenu);
            }
        } else {
            alertAndErrorMessages.unspecifiedDialog();
        }
    }


    private boolean showPersonEditDialog(ActionEvent actionEvent, Dish dish) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/dishEditAndAddDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Create and Edit Dish");

            EditDish controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDish(dish);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

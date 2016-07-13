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
import restaurant.model.*;

import java.util.ArrayList;
import java.util.List;

public class EditOrder {

    private ObservableList<Dish> dishData = FXCollections.observableArrayList();
    private ObservableList<PreparedDish> preparedData = FXCollections.observableArrayList();

    @FXML
    private TableView<Dish> tableDish;
    @FXML
    private TableColumn<Dish, Integer> idColumn;
    @FXML
    private TableColumn<Dish, String> nameColumn;
    @FXML
    private TableColumn<Dish, Integer> categoryColumn;

    @FXML
    private void initialize() {
    }

    private Stage dialogStage;
    private OrderWaiter order;
    private boolean okClicked = false;
    private static List<Dish> listDises;

    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private TableView<PreparedDish> tablePrepared;
    @FXML
    private TableColumn<PreparedDish, Integer> idPreparedColumn;
    @FXML
    private TableColumn<PreparedDish, String> namePreparedColumn;

    @FXML
    private TableColumn<PreparedDish, String> categoryPreparedColumn;
    @FXML
    public Label nameUser;

    @FXML
    public Label numberTable;

    public void setOrder(OrderWaiter order) {
        this.order = order;

        nameUser.setText(order.getNameUser());
        numberTable.setText(order.getNumberTable() + "");

        //-----------------------------first initialize the second table------------------------------------------------
        dishData.addAll(Main.beanDishController().selectAll());

        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("nameCategory"));

        tableDish.setItems(dishData);

        //-----------------------------second initialize the first table------------------------------------------------
        preparedData.addAll(Main.beanPreparedController().findAllDishThisOrder(order.getId()));
//        preparedData.addAll(Main.beanPreparedController().selectAll());
        idPreparedColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, Integer>("idDish"));
        namePreparedColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, String>("nameDish"));
        categoryPreparedColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, String>("categoryDish"));

        tablePrepared.setItems(preparedData);
        listDishesOrder();
    }

    private void listDishesOrder() {
        listDises = new ArrayList<>();
        for (PreparedDish pDish : tablePrepared.getItems()) {
            listDises.add(pDish.getIdDish());
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            order.setIdsDishes(editDishes());
            okClicked = true;
            dialogStage.close();
        }
    }


    private static String editDishes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listDises.size(); i++) {
            sb.append(listDises.get(i).getName()).append("\n");
        }
        return sb.toString();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {

        if (preparedData.size() == 0) {
            alertAndErrorMessages.dialogFields(dialogStage, "No valid add one more dish!");
            return false;
        } else {
            return true;
        }


    }

    public void ActionAdd(ActionEvent actionEvent) {
        PreparedDish addInDishToOrder = new PreparedDish();
//        order.setIdsDishes(Integer.toString(preparedData.size()+1));
        Dish dish = tableDish.getSelectionModel().getSelectedItem();

        if (inStock(dish)) {
            listDises.add(dish);
            addInDishToOrder.setIdDish(dish);
            addInDishToOrder.setIdUser(order.getId_user());
            addInDishToOrder.setIdOrder(order);
            addInDishToOrder.setNameDish(dish.getName());

            Main.beanPreparedController().addInDatabase(addInDishToOrder);
            preparedData.add(addInDishToOrder);
            tablePrepared.setItems(preparedData);
        }
    }

    private boolean inStock(Dish dish) {
        List<Ingredient> listIngredientsForDish = dish.getIdIngredient();
        StringBuilder sb = new StringBuilder();
        int allTrue = 0;
        for (int i = 0; i < listIngredientsForDish.size(); i++) {
            Warehouse warehouse = Main.beanWarehouseController().findByIngredient(listIngredientsForDish.get(i));
            DishIngredient dishIngredient = new DishIngredient(dish.getId(), listIngredientsForDish.get(i));
            double onceIngredient = Main.beanDishController().findInDishIngredient(dishIngredient).getQuantity();
            if (warehouse.getQuantity() >= onceIngredient) {
                allTrue++;
            } else {
                sb.append("Ingredients: ").append(warehouse.getIdIngredient().getName());
                sb.append(" quality: ").append(warehouse.getQuantity()).append(" and you must ");
                sb.append("quality: ").append(onceIngredient).append("\n");
            }
        }
        if (listIngredientsForDish.size() == allTrue) {
            System.out.println("Ok");
            pickUpFromWarehouse(dish, true);
            return true;
        } else {
            alertAndErrorMessages.dialogNotAllIngredients(dialogStage, sb.toString());
            return false;
        }
    }

    private void pickUpFromWarehouse(Dish dish, boolean addOrDelete) {
        List<Ingredient> listIngredientsForDish = dish.getIdIngredient();

        for (int i = 0; i < listIngredientsForDish.size(); i++) {
            Warehouse warehouse = Main.beanWarehouseController().findByIngredient(listIngredientsForDish.get(i));
            DishIngredient dishIngredient = new DishIngredient(dish.getId(), listIngredientsForDish.get(i));
            double onceIngredient = Main.beanDishController().findInDishIngredient(dishIngredient).getQuantity();

            if (addOrDelete) {
                warehouse.setQuantity(warehouse.getQuantity() - onceIngredient);
            } else {
                warehouse.setQuantity(warehouse.getQuantity() + onceIngredient);
            }
            Main.beanWarehouseController().updateInDatabase(warehouse);
        }

    }

    public void ActionDelete(ActionEvent actionEvent) {
        int selectedIndex = tablePrepared.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Dish dish = tablePrepared.getSelectionModel().getSelectedItem().getIdDish();
            pickUpFromWarehouse(dish, false);
            tablePrepared.getItems().remove(selectedIndex);
            Main.beanPreparedController().deleteWithDatabase(tablePrepared.getSelectionModel().getSelectedItem());
            listDises = setRemoveDish(tablePrepared.getSelectionModel().getSelectedItem().getIdDish());
        } else {// Ничего не выбрано.
            alertAndErrorMessages.unspecifiedDialog();
        }
    }

    private static List<Dish> setRemoveDish(Dish dish) {
        int index = 0;
        for (int i = 0; i < listDises.size(); i++) {
            if (listDises.get(i).equals(dish)) {
                index = i;
                break;
            }
        }
        listDises.remove(index);
        return listDises;
    }
}

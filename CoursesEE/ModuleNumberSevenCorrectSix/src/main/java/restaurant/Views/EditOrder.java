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
import restaurant.model.OrderWaiter;
import restaurant.model.PreparedDish;

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
    private PreparedDish preparedDish;
    private boolean okClicked = false;
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
        numberTable.setText(order.getNumberTable()+"");

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
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            okClicked = true;
            dialogStage.close();
        }
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
        order.setIdsDishes(Integer.toString(preparedData.size()+1));
        Dish dish = tableDish.getSelectionModel().getSelectedItem();
        addInDishToOrder.setIdDish(dish.getId());
        addInDishToOrder.setIdUser(order.getId_user());
        addInDishToOrder.setIdOrder(order.getId());
        addInDishToOrder.setNameDish(dish.getName());

        Main.beanPreparedController().addInDatabase(addInDishToOrder);
        preparedData.add(addInDishToOrder);
        tablePrepared.setItems(preparedData);
    }

    public void ActionDelete(ActionEvent actionEvent) {
        int selectedIndex = tablePrepared.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tablePrepared.getItems().remove(selectedIndex);
            Main.beanPreparedController().deleteWithDatabase(tablePrepared.getSelectionModel().getSelectedItem());
        } else {// Ничего не выбрано.
            alertAndErrorMessages.unspecifiedDialog();
        }
    }
}

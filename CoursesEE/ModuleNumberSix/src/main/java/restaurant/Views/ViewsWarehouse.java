package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.controllers.MainMenuController;
import restaurant.controllers.NewMenu;
import restaurant.jdbc.database.Warehouse;

import java.io.IOException;
import java.util.List;

public class ViewsWarehouse {
    private ObservableList<Warehouse> warehouseData = FXCollections.observableArrayList();
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    @FXML
    private TableView<Warehouse> tableWarehouse;

    @FXML
    private TableColumn<Warehouse, Integer> idColumn;

    @FXML
    private TableColumn<Warehouse, String> idIngredientColumn;

    @FXML
    private TableColumn<Warehouse, Double> quantityColumn;


    @FXML
    private TableColumn<Warehouse, String> unitColumn;

    @FXML
    private void initialize() {
        warehouseData.addAll(Main.beanWarehouseController().selectAll());

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, Integer>("id"));
        PropertyValueFactory d = new PropertyValueFactory<Warehouse, Integer>("idIngredient");
        idIngredientColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("itemWithDatabaseIngredients"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, Double>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("unit"));

        // заполняем таблицу данными
        tableWarehouse.setItems(warehouseData);
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
    public Button butSelectEnds;
    @FXML
    public Button butSearch;
    @FXML
    private TextField textSearch;

    private NewMenu newMenu = new NewMenu();
    public void ActionUser(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Warehouse selectWarehouse = tableWarehouse.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        int selectedIndex = tableWarehouse.getSelectionModel().getSelectedIndex();
        switch (button.getId()) {
            case "butAdd":
                handleNewUser(actionEvent);
                break;

            case "butDelete":
                if (selectedIndex >= 0) {
                    tableWarehouse.getItems().remove(selectedIndex);
                    Main.beanWarehouseController().deleteWithDatabase(selectWarehouse.getId());
                } else {
                    // Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butEdit":
                handleEditUser(actionEvent);
                warehouseData.set(selectedIndex, Main.beanWarehouseController().findById(selectWarehouse.getId()));
                tableWarehouse.setItems(warehouseData);

                break;

            case "butSelectAll":
                warehouseData.clear();
                warehouseData.addAll(Main.beanWarehouseController().selectAll());
                tableWarehouse.setItems(warehouseData);
                break;

            case "butSelectEnds":
                warehouseData.clear();
                warehouseData.addAll(Main.beanWarehouseController().findEndsItemsInWarehouse());
                tableWarehouse.setItems(warehouseData);
                break;

            case "butSearch":
                warehouseData.clear();
                System.out.println(textSearch.getText().toLowerCase());
                List<Warehouse> searchList = Main.beanWarehouseController().findByName(textSearch.getText().toLowerCase());
                warehouseData.addAll(searchList);
                tableWarehouse.setItems(warehouseData);
                break;

        }
    }

    @FXML
    private void handleNewUser(ActionEvent actionEvent) {
        Warehouse warehouse = new Warehouse();
        boolean okClicked = showWarehouseEditDialog(actionEvent, warehouse);
        System.out.println(okClicked);
        if (okClicked) {
            Main.beanWarehouseController().addInDatabase(warehouse);
            warehouseData.add(warehouse);
            tableWarehouse.setItems(warehouseData);
        }
    }


    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        Warehouse selectedWarehouse = tableWarehouse.getSelectionModel().getSelectedItem();
        if (selectedWarehouse != null) {
            boolean okClicked = showWarehouseEditDialog(actionEvent, selectedWarehouse);
            if (okClicked) {
                System.out.println(selectedWarehouse);
                Main.beanWarehouseController().updateInDatabase(selectedWarehouse);
            }
            System.out.println(okClicked);
        } else {
            alertAndErrorMessages.unspecifiedDialog();
        }
    }

    private boolean showWarehouseEditDialog(ActionEvent actionEvent, Warehouse warehouse) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/warehouseEditAndAddDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit Warehouse");

            EditWarehouse controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(warehouse);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    public Button butForIngredients;

    public void ActionForIngredients(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        new MainMenuController().newStage(actionEvent, "/views/viewsIngredients.fxml", "Ingredients");
    }
}

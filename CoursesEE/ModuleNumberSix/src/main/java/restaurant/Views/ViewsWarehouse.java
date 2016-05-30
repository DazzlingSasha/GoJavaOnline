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
import restaurant.Main;
import restaurant.jdbc.database.Warehouse;

import java.io.IOException;
import java.util.List;

public class ViewsWarehouse {
    private ObservableList<Warehouse> warehouseData = FXCollections.observableArrayList();

    @FXML
    private TableView<Warehouse> tableWarehouse;

    @FXML
    private TableColumn<Warehouse, Integer> idColumn;

    @FXML
    private TableColumn<Warehouse, Integer> idIngredientColumn;

    @FXML
    private TableColumn<Warehouse, Double> quantityColumn;

    @FXML
    private TableColumn<Warehouse, String> unitColumn;

    @FXML
    private void initialize() {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, Integer>("id"));
        idIngredientColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, Integer>("idIngredient"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, Double>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("unit"));

        // заполняем таблицу данными
        tableWarehouse.setItems(warehouseData);
    }

    private void initData() {
        warehouseData.addAll(Main.beanWarehouseController().selectAll());
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
                    unspecifiedField();
                }
                break;


            case "butEdit":
            handleEditUser(actionEvent);
//            warehouseData.set(selectedIndex, Main.beanWarehouseController().findByIdUser(selectUser.getId()));
            tableWarehouse.setItems(warehouseData);
            break;

            case "selectAll":
                warehouseData.clear();
                warehouseData.addAll(Main.beanWarehouseController().selectAll());
                tableWarehouse.setItems(warehouseData);
                break;

            case "butSelectEnds":
                warehouseData.clear();
                warehouseData.addAll(Main.beanWarehouseController().selectAll());
                tableWarehouse.setItems(warehouseData);
                break;

            case "butSearch":
            warehouseData.clear();
            System.out.println(textSearch.getText().toLowerCase());
//            List<Warehouse> searchList = Main.beanWarehouseController().findByNameUser(textSearch.getText().toLowerCase());
//            warehouseData.addAll(searchList);
            tableWarehouse.setItems(warehouseData);
            break;

        }
    }

    @FXML
    private void handleNewUser(ActionEvent actionEvent) {
        Warehouse warehouse = new Warehouse();
        boolean okClicked = showPersonEditDialog(actionEvent, warehouse);
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
            boolean okClicked = showPersonEditDialog(actionEvent, selectedWarehouse);
            if (okClicked) {
                System.out.println(selectedWarehouse);
                Main.beanWarehouseController().updateInDatabase(selectedWarehouse);
            }
            System.out.println(okClicked);
        } else {
            unspecifiedField();
        }
    }

    private void unspecifiedField() {
        // Ничего не выбрано.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No User Selected");
        alert.setContentText("Please select a user in the table.");

        alert.showAndWait();
    }

    private boolean showPersonEditDialog(ActionEvent actionEvent, Warehouse warehouse) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/warehouseEditDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit User");

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
}

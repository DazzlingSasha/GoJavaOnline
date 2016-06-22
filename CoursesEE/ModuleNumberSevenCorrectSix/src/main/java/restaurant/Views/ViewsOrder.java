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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.model.OrderWaiter;

import java.io.IOException;


public class ViewsOrder {


    private ObservableList<OrderWaiter> orderData = FXCollections.observableArrayList();
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

    @FXML
    public TableView<OrderWaiter> tableOrder;
    @FXML
    public TableColumn<OrderWaiter, Integer> idColumn;
    @FXML
    public TableColumn<OrderWaiter, String> nameWaiterColumn;
    @FXML
    public TableColumn<OrderWaiter, String> prepareDishColumn;
    @FXML
    public TableColumn<OrderWaiter, Integer> tableColumn;
    @FXML
    public TableColumn<OrderWaiter, Integer> openOrCloseColumn;

    @FXML
    private void initialize() {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<OrderWaiter, Integer>("id"));
        nameWaiterColumn.setCellValueFactory(new PropertyValueFactory<OrderWaiter, String>("nameUser"));
        prepareDishColumn.setCellValueFactory(new PropertyValueFactory<OrderWaiter, String>("idsDishes"));
        tableColumn.setCellValueFactory(new PropertyValueFactory<OrderWaiter, Integer>("numberTable"));
        openOrCloseColumn.setCellValueFactory(new PropertyValueFactory<OrderWaiter, Integer>("closeOrOpenOrder"));
        // заполняем таблицу данными
        tableOrder.setItems(orderData);
    }

    private void initData() {
        orderData.addAll(Main.beanOrderController().selectAll());
    }

    @FXML
    public Button butCreateOrder;
    @FXML
    public Button butDeleteOrder;
    @FXML
    public Button butCloseOrder;
    @FXML
    public Button butAddEditOrder;
    @FXML
    public Button butSelectAllOpen;
    @FXML
    public Button butSelectAllClose;
    @FXML
    public Button butSelectAll;

    public void ActionOrder(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        OrderWaiter selectOrder = tableOrder.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        int selectedIndex = tableOrder.getSelectionModel().getSelectedIndex();

        switch (button.getId()) {
            case "butCreateOrder":
                handleNewUser(actionEvent);
                break;

            case "butDeleteOrder":
                if(selectOrder.getCloseOrOpenOrder() != 1) {
                    if (selectedIndex >= 0) {
                        tableOrder.getItems().remove(selectedIndex);
                        Main.beanOrderController().deleteWithDatabase(selectOrder);
                    } else {// Ничего не выбрано.
                        alertAndErrorMessages.unspecifiedDialog();
                    }
                } else {
                    alertAndErrorMessages.unspecifiedDialogOrderClose();
                }
                break;

            case "butCloseOrder":
                if (selectedIndex >= 0) {
                    if(Main.beanOrderController().closeOrder(selectOrder.getId())) {
                        orderData.set(selectedIndex, Main.beanOrderController().findById(selectOrder.getId()));
                        tableOrder.setItems(orderData);
                    }
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butAddEditOrder":
                if (selectedIndex >= 0) {
                    if (selectOrder.getCloseOrOpenOrder() != 1) {
                        handleEditUser(actionEvent);
                        orderData.set(selectedIndex, Main.beanOrderController().findById(selectOrder.getId()));
                        tableOrder.setItems(orderData);
                    } else {
                        alertAndErrorMessages.unspecifiedDialogOrderClose();
                    }
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butSelectAllOpen":
                orderData.clear();
                orderData.addAll(Main.beanOrderController().selectAllOpenOrder());
                tableOrder.setItems(orderData);
                break;

            case "butSelectAllClose":
                orderData.clear();
                orderData.addAll(Main.beanOrderController().selectAllCloseOrder());
                tableOrder.setItems(orderData);
                break;

            case "butSelectAll":
                orderData.clear();
                orderData.addAll(Main.beanOrderController().selectAll());
                tableOrder.setItems(orderData);
                break;

        }

    }

    @FXML
    private void handleNewUser(ActionEvent actionEvent) {
        OrderWaiter order = new OrderWaiter();
        boolean okClicked = showPersonEditDialog(actionEvent, order, "/views/addOrderDialog.fxml", 0);

        System.out.println(okClicked);
        if (okClicked) {
            Main.beanOrderController().addInDatabase(order);
            orderData.add(order);
            tableOrder.setItems(orderData);
        }
    }

    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        OrderWaiter selectedOrder = tableOrder.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            boolean okClicked = showPersonEditDialog(actionEvent, selectedOrder, "/views/orderEditAndAddDialog.fxml", 1);
            if (okClicked) {
                Main.beanOrderController().updateInDatabase(selectedOrder);
            }
        } else {
            alertAndErrorMessages.unspecifiedDialog();
        }
    }


    private boolean showPersonEditDialog(ActionEvent actionEvent, OrderWaiter order, String dialogFile, int addOrEdit) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(dialogFile));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Order");

            if(addOrEdit == 1) {
                EditOrder controller =  getEditOrder(order, dialogStage, loader);
                dialogStage.showAndWait();
                return controller.isOkClicked();
            } else {
                AddOrder controller =  getAddOrder(order, dialogStage, loader);
                dialogStage.showAndWait();
                return controller.isOkClicked();
            }
            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private EditOrder getEditOrder(OrderWaiter order, Stage dialogStage, FXMLLoader loader) {
        EditOrder controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setOrder(order);
        return controller;
    }
    private AddOrder getAddOrder(OrderWaiter order, Stage dialogStage, FXMLLoader loader) {
        AddOrder controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setOrder(order);
        return controller;
    }
}

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.Menu;

import java.io.IOException;

public class ViewsMenu {
    private ObservableList<Dish> menuData = FXCollections.observableArrayList();

    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();
    @FXML
    public TableView<Dish> tableMenu;
    @FXML
    public TableColumn<Dish, Integer> idColumn;
    @FXML
    public TableColumn<Dish, String> nameColumn;
    @FXML
    public TableColumn<Dish, String> categoryColumn;
    @FXML
    public TableColumn<Dish, Integer> costColumn;
    @FXML
    public TableColumn<Dish, Integer> weightColumn;
    @FXML
    public ComboBox<Menu> butSelectMenu;


    @FXML
    private void initialize() {
        initData();

        butSelectMenu.setPromptText("Category");
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("nameCategory"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("cost"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("weight"));

        // заполняем таблицу данными
        tableMenu.setItems(menuData);
    }

    private void initData() {
        butSelectMenu.getItems().addAll(Main.beanMenuController().selectAll());
        menuData.addAll(Main.beanDishController().selectAll());
    }

    @FXML
    public Button butNewCategory;
    @FXML
    public Button butEditCategory;
    @FXML
    public Button butDeleteMenuAndAllDish;
    @FXML
    public Button butDeleteCategoryDish;
    @FXML
    public Button butSetDishCategory;
    @FXML
    public Button butSelectOneCategory;
    @FXML
    public Button butSelectAll;

    public void ActionMenu(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Menu selectMenu = butSelectMenu.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        int selectedIndex = butSelectMenu.getSelectionModel().getSelectedIndex();

        switch (button.getId()) {
            case "butNewCategory":
                if (selectMenu.getId() == 1) {
                    alertAndErrorMessages.selectOtherCategoryDialog();
                } else {
                    handleNewUser(actionEvent);
                }
                break;

            case "butEditCategory":
                if (selectMenu.getId() == 1) {
                    alertAndErrorMessages.selectOtherCategoryDialog();
                } else {
                    handleEditUser(actionEvent);
                    butSelectMenu.getItems().set(selectedIndex, selectMenu);
                    refreshTable();
                }
                break;

            case "butDeleteMenuAndAllDish":
                if (selectedIndex >= 0) {
                    butSelectMenu.getItems().remove(selectedIndex);
                    Main.beanDishController().findAllDishAndDeleteCategory(selectMenu.getId());
                    Main.beanMenuController().deleteWithDatabase(selectMenu.getId());
                    refreshTable();
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butDeleteCategoryDish":
                if (tableMenu.getSelectionModel().getSelectedIndex() >= 0) {
                    int idDish = tableMenu.getSelectionModel().getSelectedItem().getId();
                    Main.beanDishController().deleteCategoryDish(idDish);
                    menuData.set(tableMenu.getSelectionModel().getSelectedIndex(), Main.beanDishController().findById(idDish));
                    tableMenu.setItems(menuData);
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butSetDishCategory":
                if (selectedIndex >= 0 && tableMenu.getSelectionModel().getSelectedIndex() >= 0) {
                    int idDish = tableMenu.getSelectionModel().getSelectedItem().getId();
                    Main.beanDishController().setDishCategory(idDish, selectMenu.getId());
                    menuData.set(tableMenu.getSelectionModel().getSelectedIndex(), Main.beanDishController().findById(idDish));
                    tableMenu.setItems(menuData);
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;
            case "butSelectOneCategory":
                if (selectedIndex >= 0) {
                    menuData.clear();
                    int idCategory = butSelectMenu.getSelectionModel().getSelectedItem().getId();
                    menuData.addAll(Main.beanDishController().selectAllDishOneCategory(idCategory));
                    tableMenu.setItems(menuData);
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butSelectAll":
                refreshTable();
                break;
        }
    }

    public void refreshTable() {
        menuData.clear();
        menuData.addAll(Main.beanDishController().selectAll());
        tableMenu.setItems(menuData);
    }

    @FXML
    private void handleNewUser(ActionEvent actionEvent) {
        Menu category = new Menu();
        boolean okClicked = showPersonEditDialog(actionEvent, category);
        System.out.println(okClicked);
        if (okClicked) {
            Main.beanMenuController().addInDatabase(category);
            butSelectMenu.getItems().add(category);
        }
    }

    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        Menu selectedMenu = butSelectMenu.getSelectionModel().getSelectedItem();
        if (selectedMenu != null) {
            boolean okClicked = showPersonEditDialog(actionEvent, selectedMenu);
            if (okClicked) {
                Main.beanMenuController().updateInDatabase(selectedMenu);
            }
        } else {
            alertAndErrorMessages.unspecifiedDialog();
        }
    }


    private boolean showPersonEditDialog(ActionEvent actionEvent, Menu menu) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/menuCategoryEditAndAddDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Create and Edit Category");

            CreateMenu controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(menu);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

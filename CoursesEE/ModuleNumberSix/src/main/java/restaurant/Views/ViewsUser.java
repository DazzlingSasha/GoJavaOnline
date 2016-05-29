package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurant.Main;
import restaurant.controllers.EditUser;
import restaurant.controllers.MainMenuController;
import restaurant.jdbc.database.Users;

import java.io.IOException;
import java.util.List;

public class ViewsUser {

    private ObservableList<Users> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<Users> tableUsers;

    @FXML
    private TableColumn<Users, Integer> idColumn;

    @FXML
    private TableColumn<Users, String> firstNameColumn;

    @FXML
    private TableColumn<Users, Integer> lastNameColumn;

    @FXML
    private TableColumn<Users, String> birthdayColumn;

    @FXML
    private TableColumn<Users, String> phoneColumn;

    @FXML
    private TableColumn<Users, String> positionUserColumn;

    @FXML
    private TableColumn<Users, Integer> salaryColumn;

    @FXML
    private void initialize() {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
//        idColumn.getCellObservableValue(0);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("birthday"));  // date??
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("phone"));
        positionUserColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("positionUser"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("salary"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);

//        tableUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getClickCount() == 2){
//                    ActionEvent actionEvent = null;
//                    actionEvent = new ActionEvent(actionEvent.getSource(), butEdit);
//                    handleEditUser(actionEvent);
//                    usersData.set(selectedIndex, Main.beanUserController().findByIdUser(selectUser.getId()));
//                    tableUsers.setItems(usersData);
//                }
//            }
//        });


//        tableUsers.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void initData() {
        List<Users> list = Main.beanUserController().selectAll();
//        for (Users aList : list) {
//            System.out.println(aList);
        usersData.addAll(list);
//        }
    }
    @FXML
    public Button butAdd;
    @FXML
    public Button butDelete;
    @FXML
    public Button butSearch;
    @FXML
    public Button butEdit;
    @FXML
    public Button selectAll;
    @FXML
    private TextField textSearch;

    public void ActionUser(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Users selectUser = tableUsers.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        switch (button.getId()) {
            case "butAdd":
                handleNewUser(actionEvent);
                break;

            case "butDelete":
                if (selectedIndex >= 0) {
                    tableUsers.getItems().remove(selectedIndex);
                    Main.beanUserController().deleteWithDatabase(selectUser.getId());
                } else {
                    // Ничего не выбрано.
                    unspecifiedField();
                }
                break;

            case "butSearch":
                usersData.clear();
                System.out.println(textSearch.getText().toLowerCase());
                List<Users> searchList = Main.beanUserController().findByNameUser(textSearch.getText().toLowerCase());
                usersData.addAll(searchList);
                tableUsers.setItems(usersData);
                break;

            case "butEdit":
                handleEditUser(actionEvent);
                usersData.set(selectedIndex, Main.beanUserController().findByIdUser(selectUser.getId()));
                tableUsers.setItems(usersData);
//                userViews.closeStage();
                break;

            case "selectAll":
                usersData.clear();
                usersData.addAll(Main.beanUserController().selectAll());
                tableUsers.setItems(usersData);
                break;
        }
    }

    @FXML
    private void handleNewUser(ActionEvent actionEvent) {
        Users user = new Users();
        boolean okClicked = showPersonEditDialog(actionEvent, user);
        System.out.println(okClicked);
        if (okClicked) {
            Main.beanUserController().addInDatabase(user);
            usersData.add(user);
            tableUsers.setItems(usersData);
        }
    }

    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        Users selectedUser = tableUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            boolean okClicked = showPersonEditDialog(actionEvent, selectedUser);
            if (okClicked) {
                System.out.println(selectedUser);
                Main.beanUserController().updateInDatabase(selectedUser);
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

    private boolean showPersonEditDialog(ActionEvent actionEvent, Users user) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/userEditDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit User");

            EditUser controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUser(user);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



}



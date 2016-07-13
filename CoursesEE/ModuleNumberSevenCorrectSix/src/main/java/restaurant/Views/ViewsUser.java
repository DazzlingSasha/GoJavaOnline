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

import org.hibernate.Session;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.model.Cook;
import restaurant.model.Position;
import restaurant.model.Users;
import restaurant.model.Waiter;

import java.io.IOException;

public class ViewsUser {

    private ObservableList<Users> usersData = FXCollections.observableArrayList();
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();

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
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("birthday"));  // date??
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("phone"));
        positionUserColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("positionUser"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("salary"));

        // заполняем таблицу данными
        tableUsers.setItems(usersData);

//        tableUsers.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void initData() {
        usersData.addAll(Main.beanUserController().selectAll());
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
                    Main.beanUserController().deleteWithDatabase(selectUser);
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butSearch":
                usersData.clear();
                usersData.addAll(Main.beanUserController().findByName(textSearch.getText().replace(" ", "")));
                tableUsers.setItems(usersData);
                break;

            case "butEdit":
                handleEditUser(actionEvent);
                usersData.set(selectedIndex, Main.beanUserController().findById(selectUser.getId()));
                tableUsers.setItems(usersData);
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
        Users user = selectUser();
        boolean okClicked = showPersonEditDialog(actionEvent, user);
        System.out.println(okClicked);
        if (okClicked) {
            Main.beanUserController().addInDatabase(user);
            usersData.add(user);
            tableUsers.setItems(usersData);
        }
    }

    private Users selectUser() {
        Users user;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add new Employee");
        alert.setHeaderText("Select employee");
        ComboBox<Position> positionUserField = new ComboBox<>();
        positionUserField.getItems().addAll(Position.values());
        alert.setContentText("Please select a user in the table");
        alert.setGraphic(positionUserField);
        alert.showAndWait();

        if (positionUserField.getSelectionModel().getSelectedItem().name().equals("WAITER")) {
            user = new Waiter();
            user.setPositionUser(positionUserField.getSelectionModel().getSelectedItem());
        } else if (positionUserField.getSelectionModel().getSelectedItem().name().equals("COOK")) {
            user = new Cook();
            user.setPositionUser(positionUserField.getSelectionModel().getSelectedItem());
        } else {
            user = new Users();
        }

        return user;
    }

    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        Users selectedUser = tableUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            boolean okClicked = showPersonEditDialog(actionEvent, selectedUser);
            if (okClicked) {
                Main.beanUserController().updateInDatabase(selectedUser);
            }
        } else {
            alertAndErrorMessages.unspecifiedDialog();
        }
    }


    private boolean showPersonEditDialog(ActionEvent actionEvent, Users user) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/userEditAndAddDialog.fxml"));
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



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
import restaurant.controllers.EditUser;
import restaurant.controllers.MainMethodController;
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
        List<Users> list = Main.beanUserController().selectAll();
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
            usersData.add(list.get(i));
        }
    }

    public Button butAdd;
    public Button butDelete;
    public Button butSearch;
    public Button butEdit;
    public Button butSelectAll;

    public void ActionUser(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if(!(source instanceof Button)){
            return;
        }

        Users selectUser = tableUsers.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        switch (button.getId()){
            case "butAdd":
                handleNewPerson(actionEvent);

                break;

            case "butDelete":
                int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
//                    tableUsers.getItems().remove(selectedIndex);
                    Main.beanUserController().deleteWithDatabase(selectUser.getId());
                } else {
                    // Ничего не выбрано.
                    unspecifiedField();
                }
                break;

            case "butSearch":
                break;

            case "butEdit":
                handleEditUser(actionEvent);
                break;

            case "butSelectAll":
                initialize();
                break;
        }
    }

    @FXML
    private void handleNewPerson(ActionEvent actionEvent) {
        Users user = new Users();
        boolean okClicked = showPersonEditDialog(actionEvent, user);
        System.out.println(okClicked);
        if (okClicked) {
            Main.beanUserController().addInDatabase(user);
            tableUsers.getItems().add(10, user);
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
//            alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No User Selected");
        alert.setContentText("Please select a user in the table.");

        alert.showAndWait();
    }

//    private void showPersonDetails(Users person) {
//        if (person != null) {
//            // Заполняем метки информацией из объекта person.
//            firstNameLabel.setText(person.getFirstName());
//            lastNameLabel.setText(person.getLastName());
//            streetLabel.setText(person.getStreet());
//            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
//            cityLabel.setText(person.getCity());
//
//            // TODO: Нам нужен способ для перевода дня рождения в тип String!
//            // birthdayLabel.setText(...);
//        } else {
//            // Если Person = null, то убираем весь текст.
//            firstNameLabel.setText("");
//            lastNameLabel.setText("");
//            streetLabel.setText("");
//            postalCodeLabel.setText("");
//            cityLabel.setText("");
//            birthdayLabel.setText("");
//        }
//    }

    private boolean showPersonEditDialog(ActionEvent actionEvent, Users user){
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/userEditDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit User");
//            dialogStage.showAndWait();

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



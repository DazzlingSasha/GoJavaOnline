package restaurant.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class MainMenuController {
    @FXML
    public Button butMenu;
    @FXML
    public Button butUsers;
    @FXML
    public Button butDishes;
    @FXML
    public Button butOrder;
    @FXML
    public Button butHistory;
    @FXML
    public Button butWarehouse;

    public MainMenuController() {
    }

    public void ActionMainMenu(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button button = (Button) source;
        switch (button.getId()) {
            case "butMenu":
                newStage(actionEvent, "/views/viewsMenu.fxml", "Menu");
                break;
            case "butUsers":
                newStage(actionEvent, "/views/viewsUser.fxml", "Users");
                break;
            case "butDishes":
                newStage(actionEvent, "/views/viewsDish.fxml", "Dishes");
                break;
            case "butOrder":
                newStage(actionEvent, "/views/viewsOrder.fxml", "Order");
                break;
            case "butHistory":
                newStage(actionEvent, "/views/viewsHistory.fxml", "History");
                break;
            case "butWarehouse":
                newStage(actionEvent, "/views/viewsWarehouse.fxml", "Warehouse");
                break;
        }
    }

    public void newStage(ActionEvent actionEvent, String views, String title) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(views));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(root));
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.setTitle("Everything \"" + title + "\"");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

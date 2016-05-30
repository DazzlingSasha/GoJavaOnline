package restaurant.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class MainMenuController {
    public Button butMenu;
    public Button butUsers;
    public Button butDishes;
    public Button butOrder;
    public Button butHistory;
    public Button butWarehouse;

    public MainMenuController() {

    }
    private ActionEvent actionEvent;
    public void ActionMainMenu(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button button = (Button) source;
        switch (button.getId()) {
            case "butMenu":
                System.out.println("butMenu" + 3);
                break;
            case "butUsers":
                newStage(actionEvent, "/views/viewsUser.fxml", "Users");
                break;
            case "butDishes":
                newStage(actionEvent, "/views/viewsDish.fxml", "Dishes");

                break;
            case "butOrder":
                System.out.println("butOrder");
                break;
            case "butHistory":
                System.out.println("butHistory" + 11);
                break;
            case "butWarehouse":
                System.out.println("butWarehouse");
                break;
        }
    }

    public void closeStage() {
        stage.close();
        newStage(actionEvent, "/views/viewsUser.fxml", "Users");
    }

    private static Stage stage;

    public void newStage(ActionEvent actionEvent, String views, String title) {
        Parent root = null;
        try {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource(views));
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

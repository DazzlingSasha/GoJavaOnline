package restaurant.controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreensConfiguration {
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showScreen(Parent screen) {
        primaryStage.setScene(new Scene(screen, 777, 500));
        primaryStage.show();
    }

    public void loginDialog() {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/views/viewsDish.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        showScreen(root);
    }

}

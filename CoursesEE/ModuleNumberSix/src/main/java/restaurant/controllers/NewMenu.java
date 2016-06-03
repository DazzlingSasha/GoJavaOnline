package restaurant.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class NewMenu {
    private Stage dialogStage;
    private FXMLLoader loader;
    public Stage getDialogStage() {
        return dialogStage;
    }

    public FXMLLoader showWarehouseEditDialog() {
        try {
            dialogStage = new Stage();
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/warehouseEditAndAddDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
//            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit Warehouse");
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }
}

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
import restaurant.Main;
import restaurant.jdbc.database.Dish;

import java.io.IOException;

public class ViewsDish {
    private ObservableList<Dish> dishData = FXCollections.observableArrayList();

    @FXML
    private TableView<Dish> tableDish;

    @FXML
    private TableColumn<Dish, Integer> idColumn;

    @FXML
    private TableColumn<Dish, String> nameColumn;

    @FXML
    private TableColumn<Dish, Integer> categoryColumn;

    @FXML
    private TableColumn<Dish, String> ingredientsForDishesColumn;

    @FXML
    private TableColumn<Dish, Integer> costColumn;

    @FXML
    private TableColumn<Dish, Integer> weightColumn;

    @FXML
    private void initialize() {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("category"));
        ingredientsForDishesColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("ingredientsForDishes"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("cost"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("weight"));

        // заполняем таблицу данными
        tableDish.setItems(dishData);
    }

    private void initData() {
        dishData.addAll(Main.beanDishController());
    }

    public Button butAdd;
    public Button butDelete;
    public Button butSearch;
    public Button butSelectAll;

    public void ActionDish(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }
        Dish selectDish = tableDish.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        switch (button.getId()) {
            case "butAdd":
                System.out.println("ssss1" + selectDish);
                break;
            case "butDelete":
                System.out.println("ssss2");
                break;
            case "butSearch":
                System.out.println("ssss3" + selectDish);
                break;
            case "butSelectAll":
                System.out.println("ssss4");
                break;

        }
    }
    private boolean showPersonEditDialog(ActionEvent actionEvent, Dish dish) {
        try {
            Stage dialogStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/dishEditAndAddDialog.fxml"));
            Parent editFxml = loader.load();

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(editFxml));
            dialogStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit Dish");

            EditDish controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDish(dish);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

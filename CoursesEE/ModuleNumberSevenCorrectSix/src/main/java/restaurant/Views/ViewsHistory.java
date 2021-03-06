package restaurant.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.AlertAndErrorMessages;
import restaurant.Main;
import restaurant.model.Cook;
import restaurant.model.PreparedDish;

import java.sql.Date;

public class ViewsHistory {

    private ObservableList<PreparedDish> preparedDishData = FXCollections.observableArrayList();
    private AlertAndErrorMessages alertAndErrorMessages = new AlertAndErrorMessages();
    @FXML
    public ComboBox <Cook> butSelect;
    @FXML
    public TableView<PreparedDish> tableHistory;
    @FXML
    public TableColumn<PreparedDish, Integer>  idColumn;
    @FXML
    public TableColumn<PreparedDish, String> nameColumn;
    @FXML
    public TableColumn<PreparedDish, String> userColumn;
    @FXML
    public TableColumn<PreparedDish, Integer> idOrderColumn;
    @FXML
    public TableColumn<PreparedDish, Date> dataColumn;
    @FXML
    public TableColumn<PreparedDish, String> preparedColumn;

    @FXML
    private void initialize() {
        butSelect.getItems().addAll(Main.beanUserController().allUsersCook());

        preparedDishData.addAll(Main.beanPreparedController().selectAll());

        idColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, String>("nameDish"));
        userColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, String>("nameUser"));
        idOrderColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, Integer>("idOrder"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, Date>("datePreparedDish"));
        preparedColumn.setCellValueFactory(new PropertyValueFactory<PreparedDish, String>("prepared"));

        tableHistory.setItems(preparedDishData);
    }


    @FXML
    public Button butCooked;
    @FXML
    public Button butAllDishCook;
    @FXML
    public Button butAllDish;
    @FXML
    public Button butAllPrepared;
    @FXML
    public Button butAllNotPrepared;

    public void ActionHistory(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        PreparedDish selectUser = tableHistory.getSelectionModel().getSelectedItem();
        Button button = (Button) source;
        int selectedIndex = tableHistory.getSelectionModel().getSelectedIndex();
        int selectCook = butSelect.getSelectionModel().getSelectedIndex();
        Cook cook = butSelect.getSelectionModel().getSelectedItem();
        switch (button.getId()) {
            case "butCooked":
                if (selectedIndex >= 0 && selectCook >= 0) {
                    selectUser.setPrepared(cook);
                    Main.beanPreparedController().updateInDatabase(selectUser);
                    preparedDishData.set(selectedIndex, Main.beanPreparedController().findById(selectUser.getId()));
                    tableHistory.setItems(preparedDishData);
                } else {// Ничего не выбрано.
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butAllDishCook":
                if(selectCook >= 0) {
                    preparedDishData.clear();
                    preparedDishData.addAll(cook.getCookedDishes());
                    tableHistory.setItems(preparedDishData);
                } else {
                    alertAndErrorMessages.unspecifiedDialog();
                }
                break;

            case "butAllDish":
                preparedDishData.clear();
                preparedDishData.addAll(Main.beanPreparedController().selectAll());
                tableHistory.setItems(preparedDishData);
                break;

            case "butAllPrepared":
                preparedDishData.clear();
                preparedDishData.addAll(Main.beanPreparedController().selectAllPrepared());
                tableHistory.setItems(preparedDishData);
                break;
            case "butAllNotPrepared":
                preparedDishData.clear();
                preparedDishData.addAll(Main.beanPreparedController().selectAllNotPrepared());
                tableHistory.setItems(preparedDishData);
                break;
        }
    }
}

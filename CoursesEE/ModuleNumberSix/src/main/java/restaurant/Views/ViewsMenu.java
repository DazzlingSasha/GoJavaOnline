package restaurant.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewsMenu {
    @FXML
    public TableView tableDish;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn categoryColumn;
    @FXML
    public TableColumn costColumn;
    @FXML
    public TableColumn weightColumn;
    @FXML
    public Button butAdd;
    @FXML
    public Button butDelete;
    @FXML
    public Button butSearch;
    @FXML
    public Button butSelectAll;
    @FXML
    public Button butAdd1;
    @FXML
    public Button butAdd2;
    @FXML
    public Button butAdd21;

    public void ActionMenu(ActionEvent actionEvent) {
    }
}

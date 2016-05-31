package restaurant;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurant.controllers.*;
import restaurant.jdbc.database.*;

import java.util.List;

public class Main extends Application {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("JDBC restaurant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static UsersController beanUserController() {
        return (UsersController) context.getBean("usersController");
    }

    public static List<Dish> beanDishController() {
        DishController dishDao = (DishController) context.getBean("dishController");
        return dishDao.selectAll();
    }

    public static WarehouseController beanWarehouseController() {
        return (WarehouseController) context.getBean("warehouseController");
    }
}

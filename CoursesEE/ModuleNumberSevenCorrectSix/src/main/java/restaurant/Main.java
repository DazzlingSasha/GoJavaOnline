package restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurant.controllers.*;

public class Main extends Application {
    private static ApplicationContext context;
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml", "hibernateContext.xml");
        Main main = context.getBean(Main.class);
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

    public static DishController beanDishController() {
        return (DishController) context.getBean("dishController");
    }

    public static WarehouseController beanWarehouseController() {
        return context.getBean(WarehouseController.class);
    }

    public static IngredientController beanIngredientController() {
        return context.getBean(IngredientController.class);
    }

    public static OrderController beanOrderController() {
        return context.getBean(OrderController.class);
    }

    public static PreparedController beanPreparedController() {
        return context.getBean(PreparedController.class);
    }

    public static MenuController beanMenuController() {
        return context.getBean(MenuController.class);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

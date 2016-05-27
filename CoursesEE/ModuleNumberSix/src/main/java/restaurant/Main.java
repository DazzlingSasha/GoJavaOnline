package restaurant;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurant.controllers.*;
import restaurant.jdbc.database.Dish;
import restaurant.jdbc.database.DishDao;
import restaurant.jdbc.database.Users;
import restaurant.jdbc.database.UsersDao;

import java.util.List;

public class Main extends Application {
    private static ApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        launch(args);

//        JavaToSQLQuery sqlQuery = new JavaToSQLQuery();
//        sqlQuery.selectNameIngredientsAndQuantity();
//
//        IngredientDao ingredientDao = new IngredientDao();
////        System.out.println(ingredientDao.updateInIngredients(14, "fffff4"));
//        for(int i = 9; i < 28; i++) {
//            ingredientDao.deleteIngredient(i);
//        }
//        ingredientDao.allInfoAboutIngredients().forEach(System.out::println);
//
//        MenuDao menuDao = new MenuDao();
//        menuDao.allInfoAboutMenu().forEach(System.out::println);
//
//        DishDao dishDao= new DishDao();
//        dishDao.allInfoAboutDishes().forEach(System.out::println);
//
//        WarehouseDao warehouse = new WarehouseDao();
//        warehouse.allInfoAboutWarehouse().forEach(System.out::println);

//        UsersController user = (UsersController) context.getBean("usersController");
//        user.getAllUsers().forEach(System.out::println);

//        OrderWaiterDao order = new OrderWaiterDao();
//        order.allInfoAboutOrder().forEach(System.out::println);

//        PreparedDishDao preparedDish = new PreparedDishDao();
//        preparedDish.allInfoAboutPreparedDish().forEach(System.out::println);

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
        UsersController dishDao = (UsersController) context.getBean("usersController");
        return dishDao;
//        return dishDao.selectAll();
    }

    public static List<Dish> beanDishController() {
        DishController dishDao = (DishController) context.getBean("dishController");
        return dishDao.selectAll();
    }
//    public static void
}

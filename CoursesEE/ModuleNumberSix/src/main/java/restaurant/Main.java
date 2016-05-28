package restaurant;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import restaurant.controllers.DishController;
import restaurant.controllers.UsersController;
import restaurant.jdbc.database.DishDao;
import restaurant.jdbc.database.UsersDao;

public class Main extends Application{

    private DishController dishController;

    public static void main(String[] args)  {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DishController dishDao = (DishController) context.getBean("dishController");
//        dishDao.getAllDish();
//        Main main = (Main) context.getBean("main");
//        main.start();
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

    private void start() {
        dishController.getAllDish().forEach(System.out::println);
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("JDBC restaurant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

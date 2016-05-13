package restaurant;


import restaurant.jdbc.databace.*;

public class Main {

    public static void main(String[] args) {
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

//        UsersDao user = new UsersDao();
//        user.allInfoAboutUsers().forEach(System.out::println);

//        OrderWaiterDao order = new OrderWaiterDao();
//        order.allInfoAboutOrder().forEach(System.out::println);

        PreparedDishDao preparedDish = new PreparedDishDao();
        preparedDish.allInfoAboutPreparedDish().forEach(System.out::println);
    }
}

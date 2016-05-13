package restaurant.jdbc.databace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restaurant.JavaToSQLQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDao {
    private final String url;
    private final String user;
    private final String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(DishDao.class);

    public DishDao() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }

    public List<Dish> allInfoAboutDishes() {
        List<Dish> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM DISH";
            LOGGER.info("Connect with databased DISH");
            ResultSet rs = statement.executeQuery(query);
            // executing SELECT query

            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setName(rs.getString(2));
                dish.setCategory(rs.getInt("id_category"));
                dish.setIngredientsForDishes(rs.getString("ids_ingredients_dish"));
                dish.setCost(rs.getInt("cost"));
                dish.setWeight(rs.getInt("weight"));

                String listIngredient = dish.getIngredientsForDishes();
                String queryIngredients = "SELECT NAME_INGREDIENT FROM ingredients WHERE id = ?";
                String allIngredients = JavaToSQLQuery.parserList(connection, listIngredient, queryIngredients, "NAME_INGREDIENT");

                dish.setIngredientsForDishes(allIngredients);
                result.add(dish);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }
}

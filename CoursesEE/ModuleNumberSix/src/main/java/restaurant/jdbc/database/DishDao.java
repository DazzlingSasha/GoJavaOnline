package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.JavaToSQLQuery;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DishDao.class);

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void createNewDish(String name, int idCategory, String idsIngredientsDish, int cost, int weight) {
        String query = "INSERT INTO DISH (NAME, ID_CATEGORY, IDS_INGREDIENTS_DISH, COST, WEIGHT) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased DISH and Add new Dish");
            statement.setString(1, name);
            statement.setInt(2, idCategory);
            statement.setString(3, idsIngredientsDish);
            statement.setInt(4, cost);
            statement.setInt(5, weight);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH': " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Dish> allInfoAboutDishes() {
        List<Dish> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM DISH";
            LOGGER.info("Connect with databased DISH");
            ResultSet rs = statement.executeQuery(query);
            // executing SELECT query

            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setName(rs.getString("name"));
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

package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientDao.class);

    private  final String url;
    private  final String user;
    private  final String password;


    public IngredientDao() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }

    public List<Ingredient> allInfoAboutIngredients() {
        List<Ingredient> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM ingredients";
            LOGGER.info("Connect with databased INGREDIENTS");
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getInt("id"));
                ingredient.setName(rs.getString("name_ingredient"));
                result.add(ingredient);
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }

    public void insertInIngredients(String values) {
        String query = "INSERT INTO INGREDIENTS (NAME_INGREDIENT) VALUES ( ? )";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Connect with databased INGREDIENTS");

            statement.setString(1, values);
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }
    }

    public Ingredient loadOneIngredient(int id) {
        Ingredient ingredient = null;
        String query = "SELECT * FROM ingredients WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            LOGGER.info("Connect with databased INGREDIENTS");

            while (rs.next()) {
                ingredient = new Ingredient();
                ingredient.setId(rs.getInt("id"));
                ingredient.setName(rs.getString("name_ingredient"));
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }
        return ingredient;
    }

    public Ingredient updateInIngredients(int id, String newIngredient) {
        String query = "UPDATE INGREDIENTS SET NAME_INGREDIENT = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased INGREDIENTS");
            statement.setString(1, newIngredient);
            statement.setInt(2, id);

            if (statement.executeUpdate() == 1) {
                return loadOneIngredient(id);
            } else {
                throw new RuntimeException("Cannot find id with databased INGREDIENTS: " + id);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }

    }
    public void deleteIngredient(int id) {
        String query = "DELETE FROM INGREDIENTS WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased INGREDIENTS");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }

    }


}

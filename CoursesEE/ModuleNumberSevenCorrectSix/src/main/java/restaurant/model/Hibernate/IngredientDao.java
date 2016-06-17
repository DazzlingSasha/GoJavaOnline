package restaurant.model.Hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Ingredient;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientDao.class);

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Transactional(propagation = Propagation.MANDATORY)
    public List<Ingredient> allInfoAboutIngredients() {
        List<Ingredient> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
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
    @Transactional(propagation = Propagation.MANDATORY)
    public void createInIngredients(String values) {
        String query = "INSERT INTO INGREDIENTS (NAME_INGREDIENT) VALUES ( ? )";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Connect with databased INGREDIENTS");

            statement.setString(1, values);
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Ingredient loadOneIngredient(int id) {
        Ingredient ingredient = null;
        String query = "SELECT * FROM ingredients WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            LOGGER.info("Connect with databased INGREDIENTS");

            while (rs.next()) {
                ingredient = getIngredient(rs);
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }
        return ingredient;
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Ingredient updateInIngredients(Ingredient item) {
        String query = "UPDATE INGREDIENTS SET NAME_INGREDIENT = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased INGREDIENTS");
            statement.setString(1, item.getName());
            statement.setInt(2, item.getId());

            if (statement.executeUpdate() == 1) {
                return loadOneIngredient(item.getId());
            } else {
                throw new RuntimeException("Cannot find id with databased INGREDIENTS: " + item.getId());
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }

    }
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteIngredient(int id) {
        String query = "DELETE FROM INGREDIENTS WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased INGREDIENTS");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Ingredients': " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Ingredient> findByName(String name) {
        List<Ingredient> result = new ArrayList<>();
        String query = "SELECT * FROM  ingredients WHERE ingredients.name_ingredient ILIKE " + "'%" + name + "%'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased USERS find by name");
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Ingredient item = getIngredient(resultSet);
                result.add(item);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + e);
            throw new RuntimeException();
        }

        return result;
    }

    private Ingredient getIngredient(ResultSet resultSet) throws SQLException {
        Ingredient item = new Ingredient();
        item.setId(resultSet.getInt("id"));
        item.setName(resultSet.getString("name_ingredient"));
        return item;
    }
}

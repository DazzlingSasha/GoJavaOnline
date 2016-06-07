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
    public void createNewDish(Dish dish) {
        String query = "INSERT INTO DISH (NAME, ID_CATEGORY, IDS_INGREDIENTS_DISH, COST, WEIGHT) VALUES (?, 1, 0, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased DISH and Add new Dish");
            statement.setString(1, dish.getName());
            statement.setInt(2, dish.getCost());
            statement.setInt(3, dish.getWeight());
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH' and Add new Dish: " + sqlEx);
            throw new RuntimeException();
        }

    }
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateDish(Dish dish) {
        String query = "UPDATE DISH SET NAME = ?, COST=?, WEIGHT=? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased Dish and update with id: " + dish.getId());
            statement.setString(1, dish.getName());
            statement.setInt(2, dish.getCost());
            statement.setInt(3, dish.getWeight());
            statement.setInt(4, dish.getId());

            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'Dish' and update: " + sqlEx);
            throw new RuntimeException();
        }

    }
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Dish> selectMenuJoinDish() {
        List<Dish> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT DISH.id, DISH.NAME, DISH.id_category, DISH.COST, DISH.WEIGHT, MENU.name_category FROM DISH " +
                    "INNER JOIN MENU ON MENU.ID = dish.id_category ";
            LOGGER.info("Connect with databased DISH select Menu Join Dish");
            ResultSet rs = statement.executeQuery(query);


            while (rs.next()) {
                Dish dish = getDish(rs);
                result.add(dish);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Dish> selectMenuJoinDishOneCategory(int idCategory) {
        List<Dish> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT DISH.id, DISH.NAME, DISH.id_category, DISH.COST, DISH.WEIGHT, MENU.name_category FROM DISH " +
                    "INNER JOIN MENU ON MENU.ID = dish.id_category WHERE DISH.id_category = " + idCategory;
            LOGGER.info("Connect with databased DISH select Menu Join Dish");
            ResultSet rs = statement.executeQuery(query);
            // executing SELECT query

            while (rs.next()) {
                Dish dish = getDish(rs);
                result.add(dish);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH' select Menu Join Dish: " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Dish> findDishesByNameMenuJoinDish(String nameDish) {
        List<Dish> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT DISH.id, DISH.NAME, DISH.id_category, DISH.COST, DISH.WEIGHT, MENU.name_category FROM DISH " +
                    "INNER JOIN MENU ON MENU.ID = dish.id_category WHERE DISH.name ILIKE " + "'%" + nameDish + "%'";
            LOGGER.info("Connect with databased DISH  find dishes by name Dish Join Menu");
            ResultSet rs = statement.executeQuery(query);
            // executing SELECT query

            while (rs.next()) {
                Dish dish = getDish(rs);
                result.add(dish);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH' find dishes by name Dish Join Menu: " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Dish findDishByIdJoinManu(int id) {
        Dish dish = null;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT DISH.id, DISH.NAME, DISH.id_category, DISH.COST, DISH.WEIGHT, MENU.name_category FROM DISH " +
                    "INNER JOIN MENU ON MENU.ID = dish.id_category WHERE DISH.id = " + id;
            LOGGER.info("Connect with databased DISH select dish by id Join Menu");
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                dish = getDish(rs);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH' select dish by id Join Menu: " + sqlEx);
            throw new RuntimeException();
        }
        return dish;
    }

    private Dish getDish(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("id"));
        dish.setName(resultSet.getString("name"));
        dish.setCategory(resultSet.getInt("id_category"));
        dish.setNameCategory(resultSet.getString("name_category"));
        dish.setCost(resultSet.getInt("cost"));
        dish.setWeight(resultSet.getInt("weight"));
        return dish;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateAllDishCategoryOnNOTCATEGORY(int category) {
        String query = "UPDATE DISH SET  ID_CATEGORY = 1 WHERE ID_CATEGORY = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased DISH and update category on NOT CATEGORY");
            statement.setInt(1, category);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH' and update category on NOT CATEGORY" + sqlEx);
            throw new RuntimeException();
        }

    }
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteCategory(int id) {
        String query = "UPDATE DISH SET  ID_CATEGORY = 1 WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased DISH and delete field Category with id: " + id);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH' and delete field Category" + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void setDishCategory(int id, int numberCategory) {
        String query = "UPDATE DISH SET  ID_CATEGORY = ? WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased DISH and set Category in the Dish with id: " + id);
            statement.setInt(1, numberCategory);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'DISH' set Category in the Dish" + sqlEx);
            throw new RuntimeException();
        }

    }
}

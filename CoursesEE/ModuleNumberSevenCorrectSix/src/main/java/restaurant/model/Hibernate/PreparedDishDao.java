package restaurant.model.Hibernate;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.PreparedDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedDishDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedDishDao.class);
    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<PreparedDish> allInfoAboutPreparedDish() {
        List<PreparedDish> result = new ArrayList<>();

        String query = "SELECT PREPARED_DISH.ID, DISH.NAME, PREPARED_DISH.ID_ORDER, USERS.first_name, USERS.last_name, " +
                "PREPARED_DISH.data_dish, PREPARED_DISH.prepared_dish FROM PREPARED_DISH " +
                "INNER JOIN DISH ON DISH.ID = PREPARED_DISH.ID_DISH " +
                "INNER JOIN USERS ON USERS.ID = PREPARED_DISH.ID_USER " +
                "INNER JOIN ORDER_WAITER ON ORDER_WAITER.ID = PREPARED_DISH.ID_ORDER WHERE ORDER_WAITER.open_close = 1";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased PREPARED_DISH all prepared dishes and JOIN Users and Dish");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PreparedDish preparedDish = getPrepare(resultSet);
                result.add(preparedDish);
            }
        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH': select all prepared dishes and JOIN Users and Dish" + e);
            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<PreparedDish> allPreparedDish() {
        List<PreparedDish> result = new ArrayList<>();

        String query = "SELECT PREPARED_DISH.ID, DISH.NAME, PREPARED_DISH.ID_ORDER, USERS.first_name, USERS.last_name, " +
                "PREPARED_DISH.data_dish , PREPARED_DISH.prepared_dish FROM PREPARED_DISH " +
                "INNER JOIN DISH ON DISH.ID = PREPARED_DISH.ID_DISH " +
                "INNER JOIN USERS ON USERS.ID = PREPARED_DISH.ID_USER " +
                "INNER JOIN ORDER_WAITER ON ORDER_WAITER.ID = PREPARED_DISH.ID_ORDER WHERE ORDER_WAITER.open_close = 1 AND PREPARED_DISH.prepared_dish = 1";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased PREPARED_DISH select all prepared dishes and JOIN Users and Dish where prepare_dih = 1");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PreparedDish preparedDish = getPrepare(resultSet);
                result.add(preparedDish);
            }
        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH': select all prepared dishes and JOIN Users and Dish where prepare_dih = 1" + e);
            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<PreparedDish> allNotPreparedDish() {
        List<PreparedDish> result = new ArrayList<>();

        String query = "SELECT PREPARED_DISH.ID, DISH.NAME, PREPARED_DISH.ID_ORDER, USERS.first_name, USERS.last_name, " +
                "PREPARED_DISH.data_dish, PREPARED_DISH.prepared_dish, ORDER_WAITER.open_close FROM PREPARED_DISH " +
                "INNER JOIN DISH ON DISH.ID = PREPARED_DISH.ID_DISH " +
                "INNER JOIN USERS ON USERS.ID = PREPARED_DISH.ID_USER " +
                "INNER JOIN ORDER_WAITER ON ORDER_WAITER.ID = PREPARED_DISH.ID_ORDER WHERE ORDER_WAITER.open_close = 1 AND PREPARED_DISH.prepared_dish = 0";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased PREPARED_DISH select all prepared dishes and JOIN Users and Dish where prepared_dish = 0");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PreparedDish preparedDish = getPrepare(resultSet);
                result.add(preparedDish);
            }
        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH': select all prepared dishes and JOIN Users and Dish where prepared_dish = 0" + e);
            throw new RuntimeException();
        }

        return result;
    }
    private PreparedDish getPrepare(ResultSet resultSet) throws SQLException{
        PreparedDish preparedDish = new PreparedDish();
        preparedDish.setId(resultSet.getInt("id"));
        preparedDish.setNameDish(resultSet.getString("name"));
        preparedDish.setNameUser(resultSet.getString("first_name").replace(" ", "") +" "+ resultSet.getString("last_name").replace(" ", ""));
        preparedDish.setIdOrder(resultSet.getInt("id_order"));
        preparedDish.setDatePreparedDish(resultSet.getDate("data_dish"));
        preparedDish.setPrepared(resultSet.getInt("prepared_dish"));
        return preparedDish;
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public List<PreparedDish> allDishesThisOrder(int numberOrder) {
        List<PreparedDish> result = new ArrayList<>();
        System.out.println("------------------------------------------------------------------");
        String query = "SELECT PREPARED_DISH.id, PREPARED_DISH.id_dish, DISH.NAME, MENU.name_category, PREPARED_DISH.ID_ORDER, PREPARED_DISH.data_dish " +
                "FROM PREPARED_DISH " +
                "INNER JOIN DISH ON DISH.ID = PREPARED_DISH.ID_DISH " +
                "INNER JOIN MENU ON MENU.ID = dish.id_category WHERE PREPARED_DISH.ID_ORDER = "+ numberOrder;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased PREPARED_DISH find all dishes this order: "+ numberOrder);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PreparedDish preparedDish = new PreparedDish();
                preparedDish.setId(resultSet.getInt("id"));
                preparedDish.setIdDish(resultSet.getInt("id_dish"));
                preparedDish.setNameDish(resultSet.getString("name"));
                preparedDish.setCategoryDish(resultSet.getString("name_category"));
                preparedDish.setIdOrder(resultSet.getInt("id_order"));
//                preparedDish.formatDatePreparedDish(resultSet.getTime("data_dish"));
                result.add(preparedDish);
            }
        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH' find all dishes this order: " + e);
            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void createInPreparedDish(PreparedDish preparedDish) {
        String query = "INSERT INTO PREPARED_DISH (id_dish, id_user, id_order) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased PREPARED_DISH and Add new dish to order: "+ preparedDish.getIdOrder());
            statement.setInt(1, preparedDish.getIdDish());
            statement.setInt(2, preparedDish.getIdUser());
            statement.setInt(3, preparedDish.getIdOrder());
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH' and Add new dish to order: " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateStatusCooked(PreparedDish dish) {
        String query = "UPDATE PREPARED_DISH SET PREPARED_DISH = 1 WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased PREPARED_DISH and status dish cooked");
            statement.setInt(1, dish.getId());
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH' and status dish cooked: " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public PreparedDish findByIdPreparedDish(int id) {
        PreparedDish preparedDish = null;
        String query = "SELECT PREPARED_DISH.ID, DISH.NAME, PREPARED_DISH.ID_ORDER, USERS.first_name, USERS.last_name, " +
                "PREPARED_DISH.data_dish, PREPARED_DISH.prepared_dish FROM PREPARED_DISH " +
                "INNER JOIN DISH ON DISH.ID = PREPARED_DISH.ID_DISH " +
                "INNER JOIN USERS ON USERS.ID = PREPARED_DISH.ID_USER WHERE PREPARED_DISH.id = " + id;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased PREPARED_DISH find by id: " + id);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                preparedDish = getPrepare(resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH' find by id: " + e);
            throw new RuntimeException();
        }

        return preparedDish;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteAllDishesByOrder(int idOrder) {
        String query = "DELETE FROM PREPARED_DISH WHERE id_order = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Connect with databased PREPARED_DISH delete all dishes by order where idOrder: " + idOrder);
            statement.setInt(1, idOrder);
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH'  delete all dishes by order: " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deletePreparedDish(int id) {
        String query = "DELETE FROM PREPARED_DISH WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Connect with databased PREPARED_DISH delete dish where id: " + id);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH' delete dish where id: " + sqlEx);
            throw new RuntimeException();
        }

    }
}

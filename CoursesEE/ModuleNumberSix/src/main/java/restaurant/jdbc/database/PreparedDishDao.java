package restaurant.jdbc.database;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedDishDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedDishDao.class);
    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

//    ,
    public List<PreparedDish> allInfoAboutPreparedDish() {
        List<PreparedDish> result = new ArrayList<>();
        System.out.println("------------------------------------------------------------------");
        String query = "SELECT PREPARED_DISH.ID, DISH.NAME, PREPARED_DISH.ID_ORDER, USERS.first_name, USERS.last_name, PREPARED_DISH.data_dish " +
                "FROM PREPARED_DISH " +
                "INNER JOIN DISH ON DISH.ID = PREPARED_DISH.ID_DISH " +
                "INNER JOIN USERS ON USERS.ID = PREPARED_DISH.ID_USER ";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased PREPARED_DISH");
            System.out.println("-------------3-----------------------------------------------------");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PreparedDish preparedDish = new PreparedDish();
                preparedDish.setId(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getInt("id_order"));
                System.out.println(resultSet.getString("first_name"));
                System.out.println(resultSet.getString("last_name"));
//                preparedDish.setIdUser(resultSet.getString("first_name") +" "+ resultSet.getString("last_name"));

                String queryDish = "SELECT NAME FROM DISH WHERE id = ?";
//                preparedDish.setIdsDishes(JavaToSQLQuery.parserList(connection, resultSet.getString("ids_dishes"), queryDish, "NAME"));
                System.out.println(queryDish);
//                preparedDish.setId(resultSet.getInt("number_table"));
                preparedDish.formatDatePreparedDish(resultSet.getTime("data_dish"));
                result.add(preparedDish);
            }
            System.out.println("-------------5-----------------------------------------------------");
        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'PREPARED_DISH': " + e);
            throw new RuntimeException();
        }

        return result;
    }

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
                preparedDish.formatDatePreparedDish(resultSet.getTime("data_dish"));
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
}

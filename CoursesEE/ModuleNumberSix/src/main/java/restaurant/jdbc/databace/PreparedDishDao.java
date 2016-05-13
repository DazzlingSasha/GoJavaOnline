package restaurant.jdbc.databace;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restaurant.JavaToSQLQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedDishDao {
    private final String url;
    private final String user;
    private final String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(PreparedDishDao.class);

    public PreparedDishDao() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }
//    ,
    public List<PreparedDish> allInfoAboutPreparedDish() {
        List<PreparedDish> result = new ArrayList<>();
        System.out.println("------------------------------------------------------------------");
        String query = "SELECT PREPARED_DISH.ID, DISH.NAME, PREPARED_DISH.ID_ORDER, USERS.first_name, USERS.last_name, PREPARED_DISH.data_dish " +
                "FROM PREPARED_DISH " +
                "INNER JOIN DISH ON DISH.ID = PREPARED_DISH.ID_DISH " +
                "INNER JOIN USERS ON USERS.ID = PREPARED_DISH.ID_USER ";
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
                preparedDish.setIdUser(resultSet.getString("first_name") +" "+ resultSet.getString("last_name"));

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
}

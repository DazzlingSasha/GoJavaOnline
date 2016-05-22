package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restaurant.JavaToSQLQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderWaiterDao {
    private final String url;
    private final String user;
    private final String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWaiterDao.class);

    public OrderWaiterDao() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }

    public List<OrderWaiter> allInfoAboutOrder() {
        List<OrderWaiter> result = new ArrayList<>();
        String query = "SELECT * FROM  ORDER_WAITER";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased ORDER_WAITER");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                OrderWaiter order = new OrderWaiter();
                order.setId(resultSet.getInt("id"));
                order.setId(resultSet.getInt("id_user"));

                String queryDish = "SELECT NAME FROM DISH WHERE id = ?";
                order.setIdsDishes(JavaToSQLQuery.parserList(connection, resultSet.getString("ids_dishes"), queryDish, "NAME"));

                order.setId(resultSet.getInt("number_table"));
                order.formatDateOrder(resultSet.getTime("data_dish"));
                result.add(order);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER': " + e);
            throw new RuntimeException();
        }

        return result;
    }
}

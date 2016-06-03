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

public class OrderWaiterDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWaiterDao.class);

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<OrderWaiter> allInfoAboutOrderJOINUsers() {
        List<OrderWaiter> result = new ArrayList<>();
        String query = "SELECT ORDER_WAITER.ID, ORDER_WAITER.id_user, ORDER_WAITER.ids_dishes, ORDER_WAITER.number_table,Users.first_name, users.last_name FROM  ORDER_WAITER INNER JOIN Users ON ORDER_WAITER.ID_USER = USERS.ID";
//        String query = "SELECT * FROM  ORDER_WAITER";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased ORDER_WAITER with JOIN User");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                OrderWaiter order = getOrder(resultSet);
                result.add(order);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER': " + e);
            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteOrder(int id) {
        String query = "DELETE FROM order_waiter WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased ORDER_WAITER where id: " + id);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER': " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public boolean closeOrder(int id) {
        String query = "UPDATE ORDER_WAITER SET OPEN_CLOSE = 1 WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Connect with databased ORDER_WAITER and close order where id = " + id);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER' close order: " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<OrderWaiter> allOpenOrCloseOrder(int targeet) {
        List<OrderWaiter> result = new ArrayList<>();
        String query = "SELECT ORDER_WAITER.ID, ORDER_WAITER.id_user, ORDER_WAITER.ids_dishes, ORDER_WAITER.number_table,Users.first_name, users.last_name FROM  ORDER_WAITER INNER JOIN Users ON ORDER_WAITER.ID_USER = USERS.ID WHERE open_close = "+ targeet;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Connect with databased ORDER_WAITER and all open order ");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                OrderWaiter order = getOrder(resultSet);
                result.add(order);
            }
            return result;
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER' all open order: " + sqlEx);
            throw new RuntimeException();
        }

    }



    private OrderWaiter getOrder(ResultSet resultSet) throws SQLException {
        OrderWaiter order = new OrderWaiter();
        order.setId(resultSet.getInt("id"));
        order.setId_user(resultSet.getInt("id_user"));
        order.setIdsDishes(resultSet.getString("ids_dishes"));
        order.setNumberTable(resultSet.getInt("number_table"));
        order.setNameUser(resultSet.getString("first_name") +" "+ resultSet.getString("last_name"));
        order.setCloseOrOpenOrder(resultSet.getInt("open_close"));
//                String queryDish = "SELECT NAME FROM DISH WHERE id = ?";
//                order.setIdsDishes(JavaToSQLQuery.parserList(connection, resultSet.getString("ids_dishes"), queryDish, "NAME"));
//                order.formatDateOrder(resultSet.getTime("data_dish"));
        return order;
    }
}

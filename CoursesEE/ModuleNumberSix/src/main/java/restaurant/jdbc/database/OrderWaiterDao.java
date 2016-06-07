package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        String query = "SELECT ORDER_WAITER.ID, ORDER_WAITER.id_user, ORDER_WAITER.ids_dishes, ORDER_WAITER.number_table, ORDER_WAITER.open_close, Users.first_name, users.last_name FROM  ORDER_WAITER INNER JOIN Users ON ORDER_WAITER.ID_USER = USERS.ID";
//        String query = "SELECT * FROM  ORDER_WAITER";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased ORDER_WAITER with JOIN User");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                OrderWaiter order = getOrder(resultSet);
                String fullName = resultSet.getString("first_name").replace("  ", "") +" "+ resultSet.getString("last_name").replace("  ", "");
                order.setNameUser(fullName);
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
    public List<OrderWaiter> allOpenOrCloseOrder(int target) {
        List<OrderWaiter> result = new ArrayList<>();
        String query = "SELECT ORDER_WAITER.ID, ORDER_WAITER.id_user, ORDER_WAITER.ids_dishes, ORDER_WAITER.number_table, ORDER_WAITER.open_close, Users.first_name, users.last_name FROM  ORDER_WAITER INNER JOIN Users ON ORDER_WAITER.ID_USER = USERS.ID WHERE open_close = "+ target;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            LOGGER.info("Connect with databased ORDER_WAITER and all open order ");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                OrderWaiter order = getOrder(resultSet);
                String fullName = resultSet.getString("first_name").replace("  ", "") +" "+ resultSet.getString("last_name").replace("  ", "");
                order.setNameUser(fullName);
                result.add(order);
            }
            return result;
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER' all open order: " + sqlEx);
            throw new RuntimeException();
        }

    }
    @Transactional(propagation = Propagation.MANDATORY)
    public OrderWaiter findByIdOrder(int id) {
        OrderWaiter order = null;
        String query = "SELECT * FROM  ORDER_WAITER WHERE id =" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased ORDER_WAITER find by id order: " + id);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                order = getOrder(resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER' find by id order: " + e);
            throw new RuntimeException();
        }
        return order;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateOrder(OrderWaiter order) {
        String query = "UPDATE ORDER_WAITER SET id_user = ?, ids_dishes=?, number_table=? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased ORDER_WAITER and update order №" + order.getId());
            statement.setInt(1, order.getId_user());
            statement.setString(2, order.getIdsDishes());
            statement.setInt(3, order.getNumberTable());
            statement.setInt(4, order.getId());
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER' and update order: " + sqlEx);
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public int createOrder(OrderWaiter order) {
        String query = "INSERT INTO ORDER_WAITER (id_user, ids_dishes, number_table, open_close) VALUES (?, ?, ?, 0)";
        int index = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            LOGGER.info("Connect with databased ORDER_WAITER and Add new order");

            statement.setInt(1, order.getId_user());
            statement.setString(2, order.getIdsDishes());
            statement.setInt(3, order.getNumberTable());
//                    order.setId(statement.getGeneratedKeys().getInt(1));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(statement.getGeneratedKeys().getInt(1));
                    index = order.getId();
                    System.out.println(order.getId());
                }
                rs.close();
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER' and Add new order: " + sqlEx);
            throw new RuntimeException();
        }
        return index;
    }

//    @Transactional(propagation = Propagation.MANDATORY)
//    public int findLastRow() {
//
//        try (Connection connection = dataSource.getConnection();
//             Statement stat = connection.createStatement();) {
//            LOGGER.info("Connect with databased ORDER_WAITER findLastRow");
////            System.out.println(statement.getGeneratedKeys().getConcurrency());
//            ResultSet resultSet = stat.executeQuery("SELECT id FROM ORDER_WAITER WHERE id = LAST_INSERT_ID");
//            return resultSet.;
//        } catch (SQLException sqlEx) {
//            LOGGER.error("An error has occurred query to the database 'ORDER_WAITER' findLastRow: " + sqlEx);
//            throw new RuntimeException();
//        }
//
//    }

    private OrderWaiter getOrder(ResultSet resultSet) throws SQLException {
        OrderWaiter order = new OrderWaiter();
        order.setId(resultSet.getInt("id"));
        order.setId_user(resultSet.getInt("id_user"));
        order.setIdsDishes(resultSet.getString("ids_dishes"));
        order.setNumberTable(resultSet.getInt("number_table"));
        order.setCloseOrOpenOrder(resultSet.getInt("open_close"));
        return order;
    }
}

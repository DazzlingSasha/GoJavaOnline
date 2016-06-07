package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersDao.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void createNewUser(String firstName, String lastName, Date birthday, String phone, String positionUser, int salary) {
        String query = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE, POSITION_USER, SALARY) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased USERS and Add new USER");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setDate(3, birthday);
            statement.setString(4, phone);
            statement.setString(5, positionUser);
            statement.setInt(6, salary);

            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteUser(int id) {
        String query = "DELETE FROM USERS WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased USERS and delete Users");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Users> findByNameUser(String firstName) {
        List<Users> result = new ArrayList<>();
        String query = "SELECT * FROM  USERS WHERE first_name ILIKE " + "'%" + firstName + "%'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased USERS find by name");
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Users user = getUsers(resultSet);
                result.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + e);
            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Users findByIdUser(int id) {
        Users user = null;
        String query = "SELECT * FROM  USERS WHERE id =" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased USERS find by id: " + id);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                user = getUsers(resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + e);
            throw new RuntimeException();
        }

        return user;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Users> allInfoAboutUsers() {
        List<Users> result = new ArrayList<>();
        String query = "SELECT * FROM  USERS";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased USERS and select all users");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Users user = getUsers(resultSet);
                result.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + e);
            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Users> allUsersWaiter() {
        List<Users> result = new ArrayList<>();
        String query = "SELECT * FROM  USERS WHERE position_user='WAITER'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased USERS and select all waiter");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Users user = getUsers(resultSet);
                result.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'USERS' and select all waiter: " + e);
            throw new RuntimeException();
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateUser(Users user) {
        String query = "UPDATE USERS SET FIRST_NAME = ?, LAST_NAME=?, BIRTHDAY=?, PHONE=?, POSITION_USER=?, SALARY=? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased USERS and Add new USER");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, user.getBirthday());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getPositionUser());
            statement.setInt(6, user.getSalary());
            statement.setInt(7, user.getId());

            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + sqlEx);
            throw new RuntimeException();
        }

    }

    private Users getUsers(ResultSet resultSet) throws SQLException {
        Users user = new Users();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setPhone(resultSet.getString("phone"));
        user.setPositionUser(resultSet.getString("position_user"));
        user.setSalary(resultSet.getInt("salary"));
        return user;
    }
}

package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;

import java.util.*;

public class UsersDao {
    private final String url;
    private final String user;
    private final String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersDao.class);

    public UsersDao() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }

    public List<Users> allInfoAboutUsers() {
        List<Users> result = new ArrayList<>();
        String query = "SELECT * FROM  USERS";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased USERS");

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setPositionUser(resultSet.getString("position_user"));
                user.setSalary(resultSet.getInt("salary"));
                result.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'USERS': " + e);
            throw new RuntimeException();
        }

        return result;
    }
}

package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restaurant.JavaToSQLQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDao {
    private final String url;
    private final String user;
    private final String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDao.class);

    public MenuDao() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }

    public List<Menu> allInfoAboutMenu() {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM MENU";
            LOGGER.info("Connect with databased Menu");
            ResultSet rs = statement.executeQuery(query);
            // executing SELECT query

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setCategory(rs.getString("name_category"));

                String listDishes = rs.getString("ids_dishes");
                String queryDishes = "SELECT NAME FROM DISH WHERE id = ?";

                String allDishes = JavaToSQLQuery.parserList(connection, listDishes, queryDishes, "NAME");

                menu.setListDishes(allDishes);
                result.add(menu);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'MENU': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }


}

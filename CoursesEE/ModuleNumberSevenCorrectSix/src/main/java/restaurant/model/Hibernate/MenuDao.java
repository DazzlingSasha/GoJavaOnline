package restaurant.model.Hibernate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDao.class);
    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Menu> allInfoAboutMenu() {
        List<Menu> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM MENU";
            LOGGER.info("Connect with databased Menu");
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getInt("id"));
                menu.setCategory(rs.getString("name_category"));
                result.add(menu);
            }
        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'MENU': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void createdCategoryMenu(Menu item) {
        String query = "INSERT INTO menu (NAME_CATEGORY) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased MENU and Add new category");
            statement.setString(1, item.getCategory());
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'MENU' and Add new category: " + sqlEx);
            throw new RuntimeException();
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void updateCategoryWithMenu(Menu item) {
        String query = "UPDATE MENU SET  NAME_CATEGORY = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased MENU and update category with id: " + item.getId());
            statement.setString(1, item.getCategory());
            statement.setInt(2, item.getId());
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'MENU' and update category: " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteMenuCategory(int id) {
        String query = "DELETE FROM MENU WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased MENU and delete menu category");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'MENU' and delete menu category: " + sqlEx);
            throw new RuntimeException();
        }

    }
}

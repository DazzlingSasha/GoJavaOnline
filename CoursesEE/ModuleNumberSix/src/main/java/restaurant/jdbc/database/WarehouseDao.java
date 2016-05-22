package restaurant.jdbc.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDao {
    private final String url;
    private final String user;
    private final String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDao.class);

    public WarehouseDao() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }

    public List<Warehouse> allInfoAboutWarehouse() {
        List<Warehouse> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM WAREHOUSE";
            LOGGER.info("Connect with databased WAREHOUSE");
            ResultSet rs = statement.executeQuery(query);
            // executing SELECT query


            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setId(rs.getInt("id"));
                warehouse.setIdIngredient(rs.getInt("id_ingredient"));
                warehouse.setQuantity(rs.getString("number_ingredient"));
                result.add(warehouse);
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }
}

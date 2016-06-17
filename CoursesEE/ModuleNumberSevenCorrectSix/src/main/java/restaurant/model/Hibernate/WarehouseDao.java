package restaurant.model.Hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Warehouse;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseDao.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void createNewWarehouse(Warehouse warehouse) {
        String query = "INSERT INTO WAREHOUSE (id_ingredient, quantity, unit) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased WAREHOUSE and Add new item");
//            statement.setInt(1, warehouse.getIdIngredient());
            statement.setDouble(2, warehouse.getQuantity());
            statement.setString(3, warehouse.getUnit());

            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + sqlEx);
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Warehouse> deleteItemsWithWarehouse(int id) {
        List<Warehouse> result = new ArrayList<>();
        String query = "DELETE FROM WAREHOUSE WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased WAREHOUSE and delete item where id: " + id);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateItemInWarehouse(Warehouse item) {
        String query = "UPDATE WAREHOUSE SET id_ingredient = ?, quantity=?, unit=? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            LOGGER.info("Connect with databased WAREHOUSE and update  Item");
//            statement.setInt(1, item.getIdIngredient());
            statement.setDouble(2, item.getQuantity());
            statement.setString(3, item.getUnit());
            statement.setInt(4, item.getId());

            statement.executeUpdate();

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE' and update  Item: " + sqlEx);
            throw new RuntimeException();
        }

    }
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Warehouse> findByNameWarehouse(String nameItem) {
        List<Warehouse> result = new ArrayList<>();
        String query = "SELECT WAREHOUSE.ID, WAREHOUSE.ID_INGREDIENT, WAREHOUSE.quantity, WAREHOUSE.unit,INGREDIENTS.NAME_INGREDIENT FROM  WAREHOUSE INNER JOIN INGREDIENTS ON WAREHOUSE.ID_INGREDIENT = ingredients.ID WHERE ingredients.name_ingredient ILIKE " + "'%" + nameItem + "%'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased WAREHOUSE find by name");
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Warehouse item = getWarehouse(resultSet);
                item.setItemWithDatabaseIngredients(resultSet.getString("NAME_INGREDIENT"));
                result.add(item);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + e);
            throw new RuntimeException();
        }

        return result;
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Warehouse findByIdWarehouse(int id) {
        Warehouse warehouse = null;
        String query = "SELECT * FROM  WAREHOUSE WHERE id =" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            LOGGER.info("Connect with databased WAREHOUSE find by id: " + id);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                warehouse = getWarehouse(resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + e);
            throw new RuntimeException();
        }

        return warehouse;
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Warehouse> allInfoAboutWarehouse() {
        List<Warehouse> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM WAREHOUSE";
            LOGGER.info("Connect with databased WAREHOUSE and select all items with warehouse");
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Warehouse warehouse = getWarehouse(rs);
                result.add(warehouse);
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Warehouse> endsItemsInWarehouseJOINIngredients() {
        List<Warehouse> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

//            String query = "SELECT * FROM WAREHOUSE WHERE quantity < 10";
            String query = "SELECT WAREHOUSE.ID, WAREHOUSE.ID_INGREDIENT, WAREHOUSE.quantity, WAREHOUSE.unit,INGREDIENTS.NAME_INGREDIENT FROM  WAREHOUSE INNER JOIN INGREDIENTS ON WAREHOUSE.ID_INGREDIENT = ingredients.ID WHERE warehouse.quantity < 10";
            LOGGER.info("Connect with databased WAREHOUSE and select all items with warehouse when quantity < 10");
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Warehouse warehouse = getWarehouse(rs);
                warehouse.setItemWithDatabaseIngredients(rs.getString("NAME_INGREDIENT"));
                result.add(warehouse);
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Warehouse> warehouseJOINIngredients() {
        List<Warehouse> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT WAREHOUSE.ID, WAREHOUSE.ID_INGREDIENT, WAREHOUSE.quantity, WAREHOUSE.unit,INGREDIENTS.NAME_INGREDIENT FROM  WAREHOUSE INNER JOIN INGREDIENTS ON WAREHOUSE.ID_INGREDIENT = ingredients.ID";
            LOGGER.info("Connect with databased WAREHOUSE and select all items with warehouse when quantity < 10");
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Warehouse warehouse = getWarehouse(rs);
                warehouse.setItemWithDatabaseIngredients(rs.getString("NAME_INGREDIENT"));
                result.add(warehouse);
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("An error has occurred query to the database 'WAREHOUSE': " + sqlEx);
            throw new RuntimeException();
        }
        return result;
    }
    public Warehouse getWarehouse(ResultSet rs) throws SQLException {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(rs.getInt("id"));
//        warehouse.setIdIngredient(rs.getInt("id_ingredient"));
        warehouse.setQuantity(rs.getDouble("quantity"));
        warehouse.setUnit(rs.getString("unit"));
        return warehouse;
    }
}

package restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restaurant.jdbc.databace.*;

import java.sql.*;


public class JavaToSQLQuery {
    private final String url;
    private final String user;
    private final String password;

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaToSQLQuery.class);

    public JavaToSQLQuery() {
        LoadDriver loadDriver = new LoadDriver();
        url = loadDriver.getUrl();
        user = loadDriver.getUser();
        password = loadDriver.getPassword();
    }

    public void selectNameIngredientsAndQuantity() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT WAREHOUSE.NUMBER_INGREDIENT, INGREDIENTS.NAME_INGREDIENT " +
                    "FROM  WAREHOUSE INNER JOIN INGREDIENTS ON WAREHOUSE.ID_INGREDIENT = ingredients.ID";

            LOGGER.info("Connect with databases WAREHOUSE and INGREDIENTS");
            ResultSet rs = statement.executeQuery(query);
            // executing SELECT query

            while (rs.next()) {
                String quantity = rs.getString("number_ingredient");
                String nameIngredient = rs.getString("NAME_INGREDIENT");
                System.out.println("Select: " + nameIngredient + " - " + quantity);
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("Can not file: " + sqlEx);
            throw new RuntimeException();
        }
    }


    public static String parserList(Connection connection, String listIngredient, String query, String name) throws SQLException {
        String[] ingredientsForDishes = listIngredient.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String ingredientsForDish : ingredientsForDishes) {
            int id = Integer.parseInt(ingredientsForDish);

            PreparedStatement prepStat = connection.prepareStatement(query);
            prepStat.setInt(1, id);
            ResultSet rsPrepared = prepStat.executeQuery();

            while (rsPrepared.next()) {
                String s = rsPrepared.getString(name);
                if (s.contains("   ")) {
                    sb.append(s.substring(0, s.indexOf("   "))).append(", ");
                } else {
                    sb.append(s).append(", ");
                }
            }
        }
        return sb.toString();
    }

}
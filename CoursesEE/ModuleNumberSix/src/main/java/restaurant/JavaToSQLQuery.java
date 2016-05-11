package restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class JavaToSQLQuery {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaToSQLQuery.class);
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:postgresql://localhost:5432/restaurant";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection


    public static void main(String args[]) {

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("111111111111111111111111111111111111111111111");
            LOGGER.info("Connect with databased----------------------------------------------------");
            System.out.println("111111111111111111111111111111111111111111111-----------------------------------");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()

             ) {
            String query = "select * from ingredients";
            String query2 = "SELECT * FROM WAREHOUSE";
            String query3 = "SELECT WAREHOUSE.NUMBER_INGREDIENT, INGREDIENTS.NAME " +
                    "FROM  WAREHOUSE INNER JOIN INGREDIENTS ON WAREHOUSE.ID_INGREDIENT = ingredients.ID";
            String query4 = "SELECT * FROM DISH";
            // opening database connection to MySQL server
            LOGGER.info("Connect with databased");
            // getting Statement object to execute query
            ResultSet rs = statement.executeQuery(query4);
            // executing SELECT query


//            while (rs.next()) {
//                int count = rs.getInt("id");
//                String text = rs.getString("name");
//                System.out.println("Total number of books in the table : " + count + " name " + text);
//            }
//            while (rs.next()) {
//                int count = rs.getInt("id");
//                String text = rs.getString("name");
//                System.out.println("Total number of books in the table : " + count + " name " + text);
//            }
//            while (rs.next()) {
//                String count = rs.getString("number_ingredient");
//                String text = rs.getString("name");
//                System.out.println("Total number of books in the table : " + text + " - " + count);
//            }

            while (rs.next()) {
//                int id = rs.getInt("id");
                String name = rs.getString("name");
                int category = rs.getInt("id_category");
                String[] ingredientsForDishes = rs.getString("ids_ingredients_dish").split(" ");
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < ingredientsForDishes.length; i++) {
                    int id = Integer.parseInt(ingredientsForDishes[i]);
                    String queryIngredients = "SELECT * FROM ingredients WHERE id = ?";
                    PreparedStatement prepStat = connection.prepareStatement(queryIngredients);
                    prepStat.setInt(1, id);
                    ResultSet rsPrepared = prepStat.executeQuery();
                    while (rsPrepared.next()) {
                        sb.append(rsPrepared.getString("name") + ", ");
                    }
                }


                int cost = rs.getInt("cost");
                int weight = rs.getInt("weight");
                System.out.println(name + " - "+weight + "g  " + sb.toString() + " cost " + cost+"grn");
            }

        } catch (SQLException sqlEx) {
            LOGGER.error("Can not file: " + sqlEx);
            throw new RuntimeException();
        }
    }

}

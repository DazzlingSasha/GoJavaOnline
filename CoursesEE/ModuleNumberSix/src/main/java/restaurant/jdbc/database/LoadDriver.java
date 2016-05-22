package restaurant.jdbc.database;

import org.slf4j.LoggerFactory;

public class LoadDriver {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoadDriver.class);
    private static final String url = "jdbc:postgresql://localhost:5432/restaurant";
    private static final String user = "root";
    private static final String password = "root";

    public LoadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
            LOGGER.info("Connect with databased PostgresSQL");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can not loading driver: " + e);
            throw new RuntimeException();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}

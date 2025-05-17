package novaTech.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class for managing database connections
 */
public class DatabaseUtil {
    private static final String PROPERTIES_FILE = "/db.properties";
    private static final Properties properties;

    // Load database properties
    static {
        try {
            properties = new Properties();
            InputStream inputStream = DatabaseUtil.class.getResourceAsStream(PROPERTIES_FILE);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Property file '" + PROPERTIES_FILE + "' not found in the classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
    }

    /**
     * Gets a connection to the database
     *
     * @return A Connection object
     * @throws SQLException If a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Closes the database connection
     *
     * @param connection The connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
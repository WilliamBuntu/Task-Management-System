package novaTech.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class for managing database connections
 * Supports both MySQL and PostgreSQL connections
 */
public class DatabaseUtil {
    private static final String PROPERTIES_FILE = "/db.properties";
    private static final Properties properties;
    private static final String DB_TYPE_PROPERTY = "dbtype";
    private static final String DB_TYPE_POSTGRES = "postgresql";
    private static final String DB_TYPE_MYSQL = "mysql";

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
        String dbType = properties.getProperty(DB_TYPE_PROPERTY, DB_TYPE_MYSQL);

        try {
            // Load the appropriate database driver
            if (DB_TYPE_POSTGRES.equalsIgnoreCase(dbType)) {
                Class.forName("org.postgresql.Driver");
            } else {
                // Default to MySQL driver
                Class.forName(properties.getProperty("driver", "com.mysql.cj.jdbc.Driver"));
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        // Configure additional PostgresSQL connection properties if needed
        if (DB_TYPE_POSTGRES.equalsIgnoreCase(dbType)) {
            Properties connectionProps = new Properties();
            connectionProps.setProperty("user", user);
            connectionProps.setProperty("password", password);

            // Optional: Add PostgreSQL specific settings
            connectionProps.setProperty("ssl", properties.getProperty("ssl", "false"));
            connectionProps.setProperty("sslmode", properties.getProperty("sslmode", "prefer"));
            connectionProps.setProperty("ApplicationName", properties.getProperty("applicationName", "NovaTechApp"));

            return DriverManager.getConnection(url, connectionProps);
        } else {
            // Default MySQL connection approach
            return DriverManager.getConnection(url, user, password);
        }
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
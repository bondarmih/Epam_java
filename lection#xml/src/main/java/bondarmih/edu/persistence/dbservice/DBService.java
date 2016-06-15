package bondarmih.edu.persistence.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bondarm on 15.06.16.
 */
public class DBService {
    private final Connection connection;
    public DBService() {
        this.connection = getPostgresConnection();
    }

    public static Connection getPostgresConnection() {
        try {
            String url = "jdbc:postgresql://localhost/catalog";
            String name = "postgres";
            String pass = "postgres";

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

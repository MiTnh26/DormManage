package Utils;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionDB {
    private static Connection conn;

    public static Connection getDataSource() throws SQLException {
        if (conn != null && !conn.isClosed()) return conn;
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setURL(AppConfig.getProperty("jdbc.url"));
        dataSource.setUser(AppConfig.getProperty("jdbc.username"));
        dataSource.setPassword(AppConfig.getProperty("jdbc.password"));
        conn = dataSource.getConnection();
        return conn;
    }
}

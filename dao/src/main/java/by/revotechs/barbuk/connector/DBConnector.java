package by.revotechs.barbuk.connector;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by VVV on 20.07.2016.
 */
public class DBConnector {
    private static DBConnector datasource;
    private ComboPooledDataSource cpds;

    private DBConnector() throws IOException, SQLException, PropertyVetoException {
        Locale.setDefault(Locale.ENGLISH);
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(bundle.getString("SQL_DB_DRIVER_CLASS"));
        cpds.setJdbcUrl(bundle.getString("SQL_DB_URL"));
        cpds.setUser(bundle.getString("SQL_DB_USERNAME"));
        cpds.setPassword(bundle.getString("SQL_DB_PASSWORD"));
        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);

        System.out.println("Load JDBC");

    }

    public static DBConnector getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DBConnector();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}

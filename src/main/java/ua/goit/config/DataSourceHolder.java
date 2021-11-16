package ua.goit.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceHolder {

    private static DataSourceHolder value;

    private final DataSource dataSource;

    private  DataSourceHolder() {
        Properties properties = AppProperties.getProperties();
        PGSimpleDataSource dataSource = initPG(properties);
        switch (properties.getProperty("db.type")) {
            case "postgres": initPG(properties);
        }
        this.dataSource = dataSource;
    }

    private PGSimpleDataSource initPG(Properties properties) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{properties.getProperty("db.host")});
        dataSource.setPortNumbers(new int[]{Integer.parseInt(properties.getProperty("db.port"))});
        dataSource.setDatabaseName(properties.getProperty("db.databaseName"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
        return dataSource;
    }

    public static DataSource getDataSource() {
        if (value == null) {
            value = new DataSourceHolder();
        }
        return value.dataSource;
    }
}

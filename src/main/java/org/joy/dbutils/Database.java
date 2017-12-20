package org.joy.dbutils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.joy.dbutils.model.Table;
import org.joy.generator.config.TypeMapping;
import org.joy.util.ObjectFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public abstract class Database {
    protected Connection connection;
    protected TypeMapping typeMapping;

    public Database(){
        this.init();
    }


    private void init(){
        typeMapping = new TypeMapping();
        typeMapping.loadMapping();
    }

    /**
     * 获得表模型
     * @param catalog
     * @param schema
     * @param tableName
     * @return
     * @throws SQLException
     */
    public abstract Table getTable(String catalog, String schema, String tableName) throws SQLException;

    public Connection getConnection() {
        return connection;
    }


    public static Connection getConnection(String jdbcProperties) {
        Configurations configs = new Configurations();
        Configuration jdbcConfig = null;
        try {
            jdbcConfig = configs.properties(new File(jdbcProperties));
        } catch (ConfigurationException e) {
            log.error("Error occured on loading "+jdbcProperties,e);
        }

        String driver = jdbcConfig.getString("jdbc.driver");
        String url = jdbcConfig.getString("jdbc.url");
        String username = jdbcConfig.getString("jdbc.username");
        String password = jdbcConfig.getString("jdbc.password");

        return getConnection(driver,url,username,password);
    }
    /**
     * Connects to the database. Attempts to load the driver and connect to this instance's url.
     */
    public static Connection getConnection(String driverClass, String url,String username,String password) {
        Driver driver = getDriver(driverClass);

        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        Connection conn;
        try {
            conn = driver.connect(url, props);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        if (conn == null) {
            throw new RuntimeException("Cannot connect to database (possibly bad driver/URL combination))");
        }
        return conn;
    }

    private static Driver getDriver(String driverClass) {
        Driver driver;
        try {
            Class<?> clazz = ObjectFactory.externalClassForName(driverClass);
            driver = (Driver) clazz.newInstance();
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            throw new RuntimeException("", e);
        }
        return driver;
    }
}

package ru.ivmiit.connection;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoJdbcTemplateImpl;

import javax.servlet.ServletException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionDB {

    private static UsersDao usersDao;
    private static Connection connection;

    public static UsersDao getUsersDao() throws IOException {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();


            //изменить путь
            properties.load(new FileInputStream("C:/Users/777/developing/DriverManagerDataSource/target/UsersDbWithDmDs-0.1./WEB-INF/classes/db.properties"));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            usersDao = new UsersDaoJdbcTemplateImpl(dataSource);
            return usersDao;
    }
}


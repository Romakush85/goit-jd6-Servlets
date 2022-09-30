package ua.goit.jdbc;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.dao.DeveloperDao;
import ua.goit.jdbc.repository.DeveloperRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, "postgres", "2362");

        DeveloperRepository developerRepository = new DeveloperRepository(dbManager);
        DeveloperDao developerDao = new DeveloperDao();
        developerDao.setDevId(100);
        developerDao.setFirstName("Roman");
        developerDao.setLastName("Kushnir");
        developerDao.setGender("male");
        developerDao.setSalary(4000);
        developerDao.setBirthDate(new Date());

        developerRepository.save(developerDao);

        DeveloperDao savedDeveloper = developerRepository.findById(100);
        System.out.println(savedDeveloper);

    }
}

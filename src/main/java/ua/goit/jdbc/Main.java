package ua.goit.jdbc;

import ua.goit.jdbc.command.*;
import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.controller.ProjectManagementSystem;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.service.converter.DeveloperConverter;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");

        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, "postgres", "2362");
        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);


        DeveloperRepository developerRepository = new DeveloperRepository(dbManager);
        DeveloperConverter developerConverter = new DeveloperConverter();
        DeveloperService developerService = new DeveloperService(developerRepository, developerConverter);

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new CreateDeveloper(view, developerService));
        commands.add(new FindDeveloperById(view, developerService));
        commands.add(new FindAllByProjectId(view, developerService));
        commands.add(new FindJavaDevelopers(view, developerService));
        commands.add(new FindMiddleDevelopers(view, developerService));
        commands.add(new GetSalaryByProjectId(view, developerRepository));

        ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem(view, commands);

        projectManagementSystem.run();
    }
}

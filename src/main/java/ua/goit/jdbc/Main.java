package ua.goit.jdbc;

import ua.goit.jdbc.command.*;
import ua.goit.jdbc.command.developerCommands.*;
import ua.goit.jdbc.command.projectCommands.*;
import ua.goit.jdbc.command.skillCommand.*;
import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.controller.ProjectManagementSystem;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.repository.SkillRepository;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.service.SkillService;
import ua.goit.jdbc.service.converter.DeveloperConverter;
import ua.goit.jdbc.service.converter.ProjectConverter;
import ua.goit.jdbc.service.converter.SkillConverter;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dbUsername = System.getenv("dbUsername");
        String dbPassword = System.getenv("dbPassword");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");

        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);


        DeveloperRepository developerRepository = new DeveloperRepository(dbManager);
        DeveloperConverter developerConverter = new DeveloperConverter();
        DeveloperService developerService = new DeveloperService(developerRepository, developerConverter);

        ProjectRepository projectRepository = new ProjectRepository(dbManager);
        ProjectConverter projectConverter = new ProjectConverter();
        ProjectService projectService = new ProjectService(projectRepository, projectConverter);

        SkillRepository skillRepository = new SkillRepository(dbManager);
        SkillConverter skillConverter = new SkillConverter();
        SkillService skillService = new SkillService(skillRepository, skillConverter);

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new CreateDeveloper(view, developerService));
        commands.add(new FindDeveloperById(view, developerService));
        commands.add(new FindAllDevelopers(view, developerService));
        commands.add(new UpdateDeveloper(view, developerService));
        commands.add(new DeleteDeveloperById(view, developerRepository, developerService));
        commands.add(new FindAllByProjectId(view, developerService));
        commands.add(new FindJavaDevelopers(view, developerService));
        commands.add(new FindMiddleDevelopers(view, developerService));
        commands.add(new GetSalaryByProjectId(view, developerRepository));
        commands.add(new FindAllProjects(view, projectService));
        commands.add(new FindAllProjectsWithNumberOfDevelopers(view, projectService, developerService));
        commands.add(new CreateProject(view, projectService));
        commands.add(new UpdateProject(view, projectService));
        commands.add(new FindProjectById(view, projectService));
        commands.add(new DeleteProjectById(view, projectService, projectRepository));
        commands.add(new CreateSkill(view, skillService));
        commands.add(new UpdateSkill(view, skillService));
        commands.add(new FindSkillById(view, skillService));
        commands.add(new FindAllSkills(view, skillService));
        commands.add(new DeleteSkillById(view, skillService, skillRepository));


        ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem(view, commands);

        projectManagementSystem.run();
    }
}

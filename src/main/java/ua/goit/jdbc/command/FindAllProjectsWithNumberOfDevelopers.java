package ua.goit.jdbc.command;

import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindAllProjectsWithNumberOfDevelopers implements Command{
    public static final String FIND_ALL_WITH_DEV_NUMBER = "find  projects with dev number";
    private final View view;
    private final ProjectService projectService;
    private final DeveloperService developerService;

    public FindAllProjectsWithNumberOfDevelopers(View view, ProjectService projectService, DeveloperService developerService) {
        this.view = view;
        this.projectService = projectService;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_ALL_WITH_DEV_NUMBER);
    }

    @Override
    public void execute() throws SQLException {

        projectService.findAllProjects().stream()
                .forEach(project -> view.write(project.toString()
                        + ", devNumber=" + developerService.findAllByProjectId(project.getProjectId()).size()));
    }
}

package ua.goit.jdbc.command;

import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindAllProjects implements Command{
    public static final String FIND_ALL = "find all projects";
    private final View view;
    private final ProjectService projectService;

    public FindAllProjects(View view, ProjectService projectService) {
        this.view = view;
        this.projectService = projectService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_ALL);
    }

    @Override
    public void execute() throws SQLException {
        projectService.findAllProjects().stream()
                .forEach(project -> view.write(project.toString()));
    }
}

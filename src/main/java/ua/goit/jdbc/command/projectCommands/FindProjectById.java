package ua.goit.jdbc.command.projectCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindProjectById implements Command {
    public static final String GET_PROJECT_BY_ID = "get project by id";
    private final View view;
    private final ProjectService projectService;

    public FindProjectById(View view, ProjectService projectService) {
        this.view = view;
        this.projectService = projectService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_PROJECT_BY_ID);
    }

    @Override
    public void execute() throws SQLException {
        Integer projectId = -1;

        while(true) {
            try {
                view.write("Enter project ID");
                projectId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        projectService.findById(projectId).ifPresentOrElse(project -> view.write(project.toString()),
                () -> view.write("No project with such ID"));
    }
}

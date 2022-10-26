package ua.goit.jdbc.command.projectCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class DeleteProjectById implements Command {
    public static final String DELETE_PROJECT = "delete project";
    private final View view;
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public DeleteProjectById(View view, ProjectService projectService, ProjectRepository projectRepository) {
        this.view = view;
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_PROJECT);
    }

    @Override
    public void execute() throws SQLException {
        Integer projectId;

        while(true) {
            try {
                view.write("Enter developer ID:");
                projectId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        projectService.findById(projectId).ifPresentOrElse(project -> projectRepository.deleteById(project.getProjectId()),
                () -> view.write("No project with such ID"));
        view.write("Project deleted!");
    }
}

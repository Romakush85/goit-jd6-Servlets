package ua.goit.jdbc.command.projectCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.dto.ProjectDto;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class UpdateProject implements Command {
    public static final String UPDATE_PROJECT = "update project";
    private final View view;
    private final ProjectService projectService;

    public UpdateProject(View view, ProjectService projectService) {
        this.view = view;
        this.projectService = projectService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_PROJECT);
    }

    @Override
    public void execute() throws SQLException {
        Integer projectId = -1;
        Integer customerId = -1;
        Integer cost = -1;

        while(true) {
            try {
                view.write("Enter project ID");
                projectId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        if(!projectService.findById(projectId).isPresent()) {
            view.write("No project with such ID");
            return;
        }

        view.write("Enter project name: ");
        String name = view.read();
        while(true) {
            try {
                view.write("Enter customer ID");
                customerId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        while(true) {
            try {
                view.write("Enter cost of project");
                cost = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }

        ProjectDto projectToUpdate = new ProjectDto(projectId, name, customerId, cost);
        Boolean isUpdated = projectService.update(projectToUpdate);
        if(isUpdated) {
            view.write("Project updated!");
        } else {
            view.write("Project didn't updated!");
        }
    }
}

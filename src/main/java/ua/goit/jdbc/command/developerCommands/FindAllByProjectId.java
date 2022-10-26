package ua.goit.jdbc.command.developerCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindAllByProjectId implements Command {
    public static final String GET_DEVELOPERS_BY_PROJECT_ID = "get developers by project id";
    private final View view;
    private final DeveloperService developerService;

    public FindAllByProjectId(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVELOPERS_BY_PROJECT_ID);
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
        developerService.findAllByProjectId(projectId).stream()
                .forEach(developer -> view.write(developer.toString()));
    }
}

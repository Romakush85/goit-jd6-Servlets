package ua.goit.jdbc.command.developerCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindJavaDevelopers implements Command {
    public static final String GET_JAVA_DEVELOPERS = "get java developers";
    private final View view;
    private final DeveloperService developerService;

    public FindJavaDevelopers(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_JAVA_DEVELOPERS);
    }

    @Override
    public void execute() throws SQLException {
        developerService.findAllJavaDevelopers().stream()
                .forEach(developer -> view.write(developer.toString()));
    }
}

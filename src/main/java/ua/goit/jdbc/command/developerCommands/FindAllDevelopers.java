package ua.goit.jdbc.command.developerCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindAllDevelopers implements Command {
    public static final String FIND_ALL_DEVELOPERS = "get all developers";
    private final View view;
    private final DeveloperService developerService;

    public FindAllDevelopers(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_ALL_DEVELOPERS);
    }

    @Override
    public void execute() throws SQLException {
        developerService.findAll().stream()
                .forEach(developer -> view.write(developer.toString()));
    }
}

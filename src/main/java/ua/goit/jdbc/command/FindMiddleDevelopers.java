package ua.goit.jdbc.command;

import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindMiddleDevelopers implements Command{
    public static final String GET_MIDDLE_DEVELOPERS = "get middle developers";
    private final View view;
    private final DeveloperService developerService;

    public FindMiddleDevelopers(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_MIDDLE_DEVELOPERS);
    }

    @Override
    public void execute() throws SQLException {
        developerService.findAllMiddleDevelopers().stream()
                .forEach(developer -> view.write(developer.toString()));
    }
}

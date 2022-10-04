package ua.goit.jdbc.command;

import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class FindDeveloperById implements Command{
    public static final String GET_DEVELOPER_BY_ID = "get developer by id";
    private final View view;
    private final DeveloperService developerService;

    public FindDeveloperById(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_DEVELOPER_BY_ID);
    }

    @Override
    public void execute() {
        Integer devId = -1;

        while(true) {
            try {
                view.write("Enter developer ID");
                devId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        developerService.findById(devId).ifPresentOrElse((developer) -> view.write(developer.toString()),
                        () -> view.write("No developer with such ID"));
    }
}

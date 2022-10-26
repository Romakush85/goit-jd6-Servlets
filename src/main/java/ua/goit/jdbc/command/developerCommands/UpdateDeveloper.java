package ua.goit.jdbc.command.developerCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateDeveloper implements Command {
    public static final String UPDATE_DEVELOPER = "update developer";
    private final View view;
    private final DeveloperService developerService;

    public UpdateDeveloper(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_DEVELOPER);
    }

    @Override
    public void execute() throws SQLException {
        Integer devId = -1;
        Integer salary = -1;
        Date birthDate;
        while(true) {
            try {
                view.write("Enter developer ID");
                devId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        if(!developerService.findById(devId).isPresent()) {
            view.write("No developer with such ID");
            return;
        }

        view.write("Enter first name: ");
        String firstName = view.read();

        view.write("Enter last name: ");
        String lastName = view.read();


        while(true) {
            try {
                view.write("Enter date of birth in format dd/mm/yyyy: ");
                birthDate = new SimpleDateFormat("dd/MM/yyy").parse(view.read());
                break;
            } catch (ParseException e) {
                view.write("Invalid value, use format dd/mm/yyyy");
            }
        }

        view.write("Enter gender: ");
        String gender = view.read();

        while(true) {
            try {
                view.write("Enter salary: ");
                salary = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }

        DeveloperDto developerToUpdate = new DeveloperDto(devId, firstName, lastName, birthDate, gender, salary);
        Boolean isUpdated = developerService.update(developerToUpdate);
        if (isUpdated) {
            view.write("Developer updated!");
        } else {
            view.write("Developer didn't updated!");
        }
    }
}

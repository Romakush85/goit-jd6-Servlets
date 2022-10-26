package ua.goit.jdbc.command.developerCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateDeveloper implements Command {
    public static final String CREATE_DEVELOPER = "create developer";
    private final View view;
    private final DeveloperService developerService;

    public CreateDeveloper(View view, DeveloperService developerService) {
        this.view = view;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_DEVELOPER);
    }

    @Override
    public void execute() {
        Integer devId = -1;
        Integer salary = -1;
        Date birthDate = new Date();

        while(true) {
            try {
                view.write("Enter developer ID");
                devId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
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

        DeveloperDto developerDto = new DeveloperDto(devId, firstName, lastName, birthDate, gender, salary);
        developerService.save(developerDto);
        view.write("Developer added!");

    }
}

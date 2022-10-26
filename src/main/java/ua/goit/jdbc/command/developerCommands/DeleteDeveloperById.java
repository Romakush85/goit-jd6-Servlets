package ua.goit.jdbc.command.developerCommands;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class DeleteDeveloperById implements Command {
    public static final String DELETE_DEVELOPER_BY_ID = "delete developer";
    private final View view;
    private final DeveloperRepository developerRepository;
    private final DeveloperService developerService;

    public DeleteDeveloperById(View view, DeveloperRepository developerRepository, DeveloperService developerService) {
        this.view = view;
        this.developerRepository = developerRepository;
        this.developerService = developerService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_DEVELOPER_BY_ID);
    }

    @Override
    public void execute() throws SQLException {
        Integer devId;

        while(true) {
            try {
                view.write("Enter developer ID:");
                devId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }

        developerService.findById(devId).ifPresentOrElse(developer -> developerRepository.deleteById(developer.getDevId()),
                () -> view.write("No developer with such ID"));
       view.write("Developer deleted!");
    }

}

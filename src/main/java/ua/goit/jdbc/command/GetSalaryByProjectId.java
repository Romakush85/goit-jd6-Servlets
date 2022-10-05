package ua.goit.jdbc.command;

import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class GetSalaryByProjectId implements Command{
    public static final String GET_SALARY_BY_PROJECT_ID = "get salary by project id";
    private final View view;
    private final DeveloperRepository developerRepository;

    public GetSalaryByProjectId(View view, DeveloperRepository developerRepository) {
        this.view = view;
        this.developerRepository = developerRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_SALARY_BY_PROJECT_ID);
    }

    @Override
    public void execute() {
        Integer projectId = null;

        while(true) {
            try {
                view.write("Enter project ID");
                projectId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        view.write(String.valueOf(developerRepository.getTotalSalaryByProjectId(projectId)));
    }

}

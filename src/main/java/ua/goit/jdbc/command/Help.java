package ua.goit.jdbc.command;

import ua.goit.jdbc.view.View;

public class Help implements Command{
    private static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter %s to see all commands", Help.HELP));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
        view.write(String.format("Enter %s to add new developer", CreateDeveloper.CREATE_DEVELOPER));
        view.write(String.format("Enter %s to find developer by Id", FindDeveloperById.GET_DEVELOPER_BY_ID));
        view.write(String.format("Enter %s to find developers by project Id", FindAllByProjectId.GET_DEVELOPERS_BY_PROJECT_ID));
        view.write(String.format("Enter %s to find Java developers", FindJavaDevelopers.GET_JAVA_DEVELOPERS));
        view.write(String.format("Enter %s to find Java developers", FindMiddleDevelopers.GET_MIDDLE_DEVELOPERS));
        view.write(String.format("Enter %s to get total salary by project", GetSalaryByProjectId.GET_SALARY_BY_PROJECT_ID));
        view.write(String.format("Enter %s to find all projects", FindAllProjects.FIND_ALL));
        view.write(String.format("Enter %s to find all projects with number of developers", FindAllProjectsWithNumberOfDevelopers.FIND_ALL_WITH_DEV_NUMBER));
    }
}

package ua.goit.jdbc.command;

import ua.goit.jdbc.command.developerCommands.*;
import ua.goit.jdbc.command.projectCommands.*;
import ua.goit.jdbc.command.skillCommand.*;
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
        view.write("----Developers commands----");
        view.write(String.format("Enter %s to add new developer", CreateDeveloper.CREATE_DEVELOPER));
        view.write(String.format("Enter %s to find developer by Id", FindDeveloperById.GET_DEVELOPER_BY_ID));
        view.write(String.format("Enter %s to find all developers", FindAllDevelopers.FIND_ALL_DEVELOPERS));
        view.write(String.format("Enter %s to update developer", UpdateDeveloper.UPDATE_DEVELOPER));
        view.write(String.format("Enter %s to delete developer by Id", DeleteDeveloperById.DELETE_DEVELOPER_BY_ID));
        view.write(String.format("Enter %s to find developers by project Id", FindAllByProjectId.GET_DEVELOPERS_BY_PROJECT_ID));
        view.write(String.format("Enter %s to find Java developers", FindJavaDevelopers.GET_JAVA_DEVELOPERS));
        view.write(String.format("Enter %s to find Middle developers", FindMiddleDevelopers.GET_MIDDLE_DEVELOPERS));
        view.write("----Projects commands----");
        view.write(String.format("Enter %s to add new project", CreateProject.CREATE_PROJECT));
        view.write(String.format("Enter %s to update project", UpdateProject.UPDATE_PROJECT));
        view.write(String.format("Enter %s to delete project", DeleteProjectById.DELETE_PROJECT));
        view.write(String.format("Enter %s to find all projects", FindAllProjects.FIND_ALL));
        view.write(String.format("Enter %s to find  project by ID", FindProjectById.GET_PROJECT_BY_ID));
        view.write(String.format("Enter %s to find all projects with number of developers", FindAllProjectsWithNumberOfDevelopers.FIND_ALL_WITH_DEV_NUMBER));
        view.write(String.format("Enter %s to get total salary by project", GetSalaryByProjectId.GET_SALARY_BY_PROJECT_ID));
        view.write("----Skills commands----");
        view.write(String.format("Enter %s to add new skill", CreateSkill.CREATE_SKILL));
        view.write(String.format("Enter %s to update skill", UpdateSkill.UPDATE_SKILL));
        view.write(String.format("Enter %s to find  skill by ID", FindSkillById.FIND_SKILL_BY_ID));
        view.write(String.format("Enter %s to find all skills", FindAllSkills.FIND_ALL_SKILLS));
        view.write(String.format("Enter %s to delete skill", DeleteSkillById.DELETE_SKILL));



    }
}

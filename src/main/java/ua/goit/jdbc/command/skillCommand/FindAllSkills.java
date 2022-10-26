package ua.goit.jdbc.command.skillCommand;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.SkillService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindAllSkills implements Command {
    public static final String FIND_ALL_SKILLS = "get all skills";
    private final View view;
    private final SkillService skillService;

    public FindAllSkills(View view, SkillService skillService) {
        this.view = view;
        this.skillService = skillService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_ALL_SKILLS);
    }

    @Override
    public void execute() throws SQLException {
        skillService.findAll().stream()
                .forEach(skill -> view.write(skill.toString()));
    }
}

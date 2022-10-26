package ua.goit.jdbc.command.skillCommand;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.service.SkillService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class FindSkillById implements Command {
    public static final String FIND_SKILL_BY_ID = "get skill by ID";
    private final View view;
    private final SkillService skillService;

    public FindSkillById(View view, SkillService skillService) {
        this.view = view;
        this.skillService = skillService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_SKILL_BY_ID);
    }

    @Override
    public void execute() throws SQLException {
        Integer skillId = -1;
        while(true) {
            try {
                view.write("Enter skill ID");
                skillId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        skillService.findById(skillId).ifPresentOrElse(skill -> view.write(skill.toString()),
                () -> view.write("No skill with such ID"));
    }
}

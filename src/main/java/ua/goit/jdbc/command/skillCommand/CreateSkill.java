package ua.goit.jdbc.command.skillCommand;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.dto.SkillDto;
import ua.goit.jdbc.service.SkillService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class CreateSkill implements Command {
    public static final String CREATE_SKILL = "create skill";
    private final View view;
    private final SkillService skillService;

    public CreateSkill(View view, SkillService skillService) {
        this.view = view;
        this.skillService = skillService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_SKILL);
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

        view.write("Enter language: ");
        String language = view.read();

        view.write("Enter level: ");
        String level = view.read();

        SkillDto skillDto = new SkillDto(skillId, language, level);
        skillService.save(skillDto);
        view.write("Skill added!");
    }
}

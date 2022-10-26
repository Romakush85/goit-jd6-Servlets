package ua.goit.jdbc.command.skillCommand;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.repository.SkillRepository;
import ua.goit.jdbc.service.SkillService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;

public class DeleteSkillById implements Command {
    public static final String DELETE_SKILL = "delete skill";
    private final View view;
    private final SkillService skillService;
    private final SkillRepository skillRepository;

    public DeleteSkillById(View view, SkillService skillService, SkillRepository skillRepository) {
        this.view = view;
        this.skillService = skillService;
        this.skillRepository = skillRepository;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_SKILL);
    }

    @Override
    public void execute() throws SQLException {
        Integer skillId;

        while(true) {
            try {
                view.write("Enter skill ID:");
                skillId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                view.write("Invalid value, use digits");
            }
        }
        skillService.findById(skillId).ifPresentOrElse(
                skill -> skillRepository.deleteById(skill.getSkillId()),
                () -> view.write("No skill with such ID"));
        view.write("Skill deleted!");
    }
}

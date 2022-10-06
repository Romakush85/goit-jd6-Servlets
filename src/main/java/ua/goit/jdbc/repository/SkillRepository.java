package ua.goit.jdbc.repository;

import ua.goit.jdbc.dao.SkillDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SkillRepository implements Repository<SkillDao>{
    private final static String INSERT = "INSERT INTO skills (skill_id, language, level) " +
            "VALUES (?, ?, ?)";
    @Override
    public SkillDao save(SkillDao entity) {
        return null;
    }

    @Override
    public void update(SkillDao entity) {

    }

    @Override
    public Optional<SkillDao> findById(Integer id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<SkillDao> findAll() {
        return null;
    }
}

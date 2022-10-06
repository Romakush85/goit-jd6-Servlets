package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.dao.DeveloperDao;
import ua.goit.jdbc.dao.ProjectDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements Repository<ProjectDao>{
    private final DatabaseManagerConnector connector;

    private final static String FIND_ALL = "SELECT project_id, project_name, cost FROM projects;";
    private final static String INSERT = "INSERT INTO projects (project_id, project_name, customer_id, cost) " +
            "VALUES (?, ?, ?, ?)";

    public ProjectRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public ProjectDao save(ProjectDao entity) {
        return null;
    }

    @Override
    public void update(ProjectDao entity) {

    }

    @Override
    public Optional<ProjectDao> findById(Integer id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<ProjectDao> findAll() {
        List<ProjectDao> projects = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    projects.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return projects;
    }

    private ProjectDao convert(ResultSet resultSet)  throws SQLException {
        ProjectDao projectDao = new ProjectDao();

        projectDao.setProjectId(resultSet.getInt("project_id"));
        projectDao.setName(resultSet.getString("project_name"));
        projectDao.setCost(resultSet.getInt("cost"));

        return projectDao;
    }
}


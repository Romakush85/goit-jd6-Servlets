package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.dao.DeveloperDao;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DeveloperRepository implements Repository<DeveloperDao>{

    private final DatabaseManagerConnector connector;

    private static final String INSERT = "INSERT INTO developers (dev_id, first_name, last_name, birth_date, gender, salary)" +
            " VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID = "SELECT dev_id, first_name, last_name, birth_date, gender, salary FROM "
        + "developers WHERE dev_id = ?";

    public  DeveloperRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public DeveloperDao save(DeveloperDao developer) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, developer.getDevId());
            statement.setString(2, developer.getFirstName());
            statement.setString(3, developer.getLastName());
            statement.setDate(4, new Date(developer.getBirthDate().getTime()));
            statement.setString(5, developer.getGender());
            statement.setInt(6, developer.getSalary());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public void update(DeveloperDao developer) {

    }

    @Override
    public Optional<DeveloperDao> findById(Integer id)  {
        DeveloperDao developerDao = null;
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                developerDao = convert(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return Optional.of(developerDao);
    }

    @Override
    public List<DeveloperDao> findAll() {
        List<DeveloperDao> developers = new ArrayList<>();
        return developers;
    }

    private DeveloperDao convert(ResultSet resultSet)  throws SQLException {
        DeveloperDao developerDao = new DeveloperDao();
        while(resultSet.next()){
            developerDao.setDevId(resultSet.getInt("dev_id"));
            developerDao.setFirstName(resultSet.getString("first_name"));
            developerDao.setLastName(resultSet.getString("last_name"));
            developerDao.setGender(resultSet.getString("gender"));
            developerDao.setSalary(resultSet.getInt("salary"));
            developerDao.setBirthDate(resultSet.getDate("birth_date"));
        }
        return developerDao;
    }
}

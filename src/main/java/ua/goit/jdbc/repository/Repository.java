package ua.goit.jdbc.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void save(T entity);
    void update(T entity);
    T findById(Integer id) throws SQLException;
    List<T> findAll();

}

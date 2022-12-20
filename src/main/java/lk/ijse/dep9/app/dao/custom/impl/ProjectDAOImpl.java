package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.ProjectDAO;
import lk.ijse.dep9.app.entity.Project;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProjectDAOImpl implements ProjectDAO {

    private Connection connection;

    public ProjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Project save(Project project) {
        return null;
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void deleteById(Integer pk) {

    }

    @Override
    public Optional<?> findById(Integer pk) {
        return Optional.empty();
    }

    @Override
    public List<?> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existById(Integer pk) {
        return false;
    }
}

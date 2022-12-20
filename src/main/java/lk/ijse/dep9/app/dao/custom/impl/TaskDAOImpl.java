package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.TaskDAO;
import lk.ijse.dep9.app.entity.Task;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TaskDAOImpl implements TaskDAO {

    private Connection connection;

    public TaskDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public void update(Task task) {

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

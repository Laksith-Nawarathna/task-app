package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.UserDAO;
import lk.ijse.dep9.app.dao.util.ConnectionUtil;
import lk.ijse.dep9.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class UserDAOImpl implements UserDAO {

    private final Connection connection;

    private final JdbcTemplate jdbc;

    public UserDAOImpl(Connection connection, JdbcTemplate jdbc) {
        this.connection = connection;
        this.jdbc = jdbc;
    }

    @Override
    public User save(User user) {

        jdbc.update("INSERT INTO User(username, full_name, password) VALUES (?, ?, ?)",
                user.getUsername(), user.getFullName(), user.getPassword());
        return user;
    }

    @Override
    public void update(User user) {

        jdbc.update("UPDATE User SET full_name=?, password=? where username=?",
                user.getFullName(),user.getPassword(), user.getUsername());

    }

    @Override
    public void deleteById(String username) {
        jdbc.update("DELETE FROM User WHERE username=?", username);
    }

    @Override
    public Optional<?> findById(String username) {

        return jdbc.query("SELECT FULL_NAME, PASSWORD FROM User WHERE username = ?", rs -> {
//            if (rs.next()){
                return Optional.of(new User(username,
                        rs.getString("password"),
                        rs.getString("full_name")));
//            }else{
//                return Optional.empty();
//            }
        }, username);
    }

    @Override
    public List<?> findAll() {

        return jdbc.query("SELECT * FROM User", rst -> {
            ArrayList<User> userList = new ArrayList<>();
            while (rst.next()){
                userList.add(new User(rst.getString("username"), rst.getString("password"),
                        rst.getString("full_name")));
            }
            return userList;
        });
    }

    @Override
    public long count() {

        return jdbc.query("SELECT COUNT(username) FROM User", rst -> {
            rst.next();
            return rst.getLong(1);
        });

    }

    @Override
    public boolean existById(String username) {
        return findById(username).isPresent();
    }
}

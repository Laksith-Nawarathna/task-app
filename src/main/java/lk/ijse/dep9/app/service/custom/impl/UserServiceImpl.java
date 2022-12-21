package lk.ijse.dep9.app.service.custom.impl;

import lk.ijse.dep9.app.dao.custom.UserDAO;
import lk.ijse.dep9.app.dao.util.ConnectionUtil;
import lk.ijse.dep9.app.dto.UserDTO;
import lk.ijse.dep9.app.service.custom.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Component
public class UserServiceImpl implements UserService {

    //private UserDAO userDAO;

    public UserServiceImpl() {

    }

    @Override
    public void createNewUserAccount(UserDTO userDTO) {

    }
}

package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.QueryDAO;
import lk.ijse.dep9.app.dao.util.ConnectionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;


@Component
@Scope("request")
public class QueryDAOImpl implements QueryDAO {

    private Connection connection;

    public QueryDAOImpl(Connection connection) {
        this.connection = ConnectionUtil.getConnection();
    }
}

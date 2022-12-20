package lk.ijse.dep9.app.dao;

import lk.ijse.dep9.app.dao.custom.impl.ProjectDAOImpl;
import lk.ijse.dep9.app.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dep9.app.dao.custom.impl.TaskDAOImpl;
import lk.ijse.dep9.app.dao.custom.impl.UserDAOImpl;

import java.sql.Connection;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Connection connection, DAOTypes daoTypes, Class<T> clazz){
        switch(daoTypes){
            case USER:
                return clazz.cast(new UserDAOImpl(connection));
            case PROJECT:
                return clazz.cast(new ProjectDAOImpl(connection));
            case TASK:
                return clazz.cast(new TaskDAOImpl(connection));
            case QUERY:
                return clazz.cast(new QueryDAOImpl(connection));
            default:
                return null;
        }
    }

}

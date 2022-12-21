package lk.ijse.dep9.app.service;

import lk.ijse.dep9.app.service.custom.impl.ProjectTaskServiceImpl;
import lk.ijse.dep9.app.service.custom.impl.UserServiceImpl;

public class ServiceFactory {

    public static ServiceFactory serviceFactory;

    public ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return (serviceFactory == null)?(serviceFactory = new ServiceFactory()): serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes serviceType, Class<T> clzz){
        switch (serviceType){
            case USER:
                return clzz.cast(new UserServiceImpl());
            case PROJECT_TASK:
                return clzz.cast(new ProjectTaskServiceImpl());
            default:
                return null;
        }
    }
}

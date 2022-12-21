package lk.ijse.dep9.app;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.annotation.RequestScope;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class WebRootConfig {

    /* a connection is created by JNDI Data Source per request (therefore new thread)
     and only managed until response is created*/
    @Bean
    public JndiObjectFactoryBean dataSource(){
        JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
        jndi.setJndiName("java:comp/env/jdbc/task-app");
        jndi.setExpectedType(DataSource.class);
        return jndi;
    }

    /* a connection is created per request and only managed until response created*/
//    @Bean
//    @RequestScope
//    public Connection connection(DataSource ds) throws SQLException {
//        return DataSourceUtils.getConnection(ds); // to work with transactions DataSourceUtils is required
//    }

    /* Here jdbc template makes a connection for an incoming request and associate it with a new thread */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds){
        return new JdbcTemplate(ds);
    }

    /* Platform transaction manager */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource ds){
        return new DataSourceTransactionManager(ds);
    }

    /* for entity dto transform */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}

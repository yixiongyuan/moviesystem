package config;

import DAO.MovieDaoImpl;
import DAO.MovieDaoLocalImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MovieConfig {

    @Bean
    public DataSource getDataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://java-final-project.cfsnshynqr7q.us-east-1.rds.amazonaws.com:3306/java-final-project");
        dataSource.setUsername("admin");
        dataSource.setPassword("javayy3454");

        return dataSource;
    }

    @Bean(name ="DAOBean")
    public MovieDaoImpl movieDAO(){

        return new MovieDaoImpl(getDataSource());
    }

    @Bean(name ="DAOLocalBean")
    public MovieDaoLocalImpl movieLocalDAO(){

        return new MovieDaoLocalImpl(getDataSource());
    }
}

package pl.sobczakpiotr.configs;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */
@Configuration
@EnableTransactionManagement
public class AppConfig {

  @Autowired
  Environment environment;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
    dataSource.setUrl(environment.getProperty("spring.datasource.url"));
    dataSource.setUsername(environment.getProperty("spring.datasource.user"));
    dataSource.setPassword(environment.getProperty("jdbc.pass"));

    return dataSource;
  }
}

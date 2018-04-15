package pl.sobczakpiotr.configs;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */

@Configuration
@EnableTransactionManagement
public class AppConfig {

  @Autowired
  private Environment environment;


  @Autowired
  @Lazy
  private DataSource dataSource;

  @Autowired
  @Lazy
  private LocalContainerEntityManagerFactoryBean entityManagerFactory;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
    dataSource.setUrl(environment.getProperty("spring.datasource.url"));
    dataSource.setUsername(environment.getProperty("spring.datasource.username"));
    dataSource.setPassword(environment.getProperty("spring.datasource.password"));

    return dataSource;
  }

  @Bean
  @DependsOn("dataSource")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dataSource);
    entityManagerFactory.setPackagesToScan("pl.sobczakpiotr.model");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

    Properties additionalProperties = new Properties();
    additionalProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
    additionalProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
    additionalProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
//    additionalProperties.put("hibernate.id.new_generator_mappings","false");
    entityManagerFactory.setJpaProperties(additionalProperties);
    return entityManagerFactory;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
    return transactionManager;
  }

}

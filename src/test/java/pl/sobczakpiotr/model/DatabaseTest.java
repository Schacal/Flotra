package pl.sobczakpiotr.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sobczakpiotr.configs.AppConfig;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, UserDaoImpl.class})
@TestPropertySource("classpath:test.properties")
public class DatabaseTest {

  @Autowired
  protected UserDao userDao;

  @Autowired
  private DataSource dataSource;

  @Test
  public void dataSourceShouldNotBeNull() {
    Assert.assertNotNull(dataSource);
  }

  @After
  public void tearDown() throws SQLException {
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute("DELETE FROM USER");
  }
}
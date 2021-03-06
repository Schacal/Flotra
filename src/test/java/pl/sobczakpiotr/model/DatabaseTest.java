package pl.sobczakpiotr.model;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sobczakpiotr.configs.AppConfig;
import pl.sobczakpiotr.model.car.CarDao;
import pl.sobczakpiotr.model.car.CarDaoImpl;
import pl.sobczakpiotr.model.user.UserDao;
import pl.sobczakpiotr.model.user.UserDaoImpl;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, UserDaoImpl.class, CarDaoImpl.class})
@TestPropertySource("classpath:test.properties")
public class DatabaseTest {

  @Autowired
  protected UserDao userDao;

  @Autowired
  protected CarDao carDao;

  @Autowired
  protected DataSource dataSource;
  protected final String UNIQUE_VALUE = String.valueOf(System.currentTimeMillis());

  @Test
  public void dataSourceShouldNotBeNull() {
    assertNotNull(dataSource);
  }

  @After
  public void tearDown() throws SQLException {
    Connection connection = dataSource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute("DELETE FROM cardetails");
    statement.execute("DELETE FROM company");
    statement.execute("DELETE FROM car");
    statement.execute("DELETE FROM \"user\"");
  }

  protected void printTables() {
    try {
      ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SHOW TABLES");
      List<String> tables = new ArrayList<>();
      while (resultSet.next()) {
        tables.add(resultSet.getString(1));
      }
      System.out.println("TABLES IN DB: " + tables);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
package pl.sobczakpiotr.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */
public class DatabaseTest {

  protected static EntityManagerFactory entityManagerFactory;
  protected static EntityManager entityManager;

  @BeforeClass
  public static void init() {
    entityManagerFactory = Persistence.createEntityManagerFactory("JunitTest");
    entityManager = entityManagerFactory.createEntityManager();
  }

  @Before
  public void initializeDatabase() throws Exception {
    Session session = entityManager.unwrap(Session.class);
    session.doWork(new Work() {
      @Override
      public void execute(Connection connection) throws SQLException {
        try {
          File script = new File(getClass().getResource("/data.sql").getFile());
          RunScript.execute(connection, new FileReader(script));
        } catch (FileNotFoundException e) {
          throw new RuntimeException("could not initialize with script");
        }
      }
    });
  }

  @AfterClass
  public static void tearDown() {
    entityManager.clear();
    entityManager.close();
    entityManagerFactory.close();
  }
}
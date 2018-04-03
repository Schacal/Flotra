package pl.sobczakpiotr.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.sobczakpiotr.configs.AppConfig;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, UserDaoImpl.class})
@TestPropertySource( "classpath:application.properties" )
public class UserDaoImplTest{

  @Autowired
  UserDao userDao;

  @Test
  public void shouldSaveNewUserInDatabase() {
    int userId = 1;
    User user = new User( userId,"TestUserName", "TestPassword", "email@test.pl" );
    userDao.createUser( user );

    User actualUser = userDao.findByUserID( userId );

    Assert.assertEquals(userId, actualUser.getId());
  }
}

package pl.sobczakpiotr.model;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sobczakpiotr.configs.AppConfig;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, UserDaoImpl.class})
public class UserDaoImplTest{

  @Autowired
  UserDaoImpl userDao;

  @Test
  public void getAll() {
    List all = userDao.getAll();
    System.out.println(all);
  }
}
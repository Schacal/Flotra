package pl.sobczakpiotr.model.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Test;
import pl.sobczakpiotr.model.DatabaseTest;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 15-04-2018
 */
public class CompanyDaoTest extends DatabaseTest {

  private final String companyName = "Company Name";

  @Test
  public void shouldCreateUserWithCompany() {
    String userName = "userName";
    UserEntity userEntity = createUser(userName);
    CompanyEntity company = createCompany();
    company.setUserEntity(userEntity);
    userEntity.setCompanyEntity(company);

    userDao.createUser(userEntity);
    Optional<UserEntity> userOptional = userDao.findUserByName(userName);
    assertTrue(userOptional.isPresent());
    UserEntity userEntity1 = userOptional.get();
    assertEquals(userName, userEntity1.getUserName());
    assertEquals(companyName, userEntity1.getCompanyEntity().getName());

  }

  private UserEntity createUser(String userName) {
    UserEntity userEntity = new UserEntity();
    userEntity.setPassword("password");
    userEntity.setUserName(userName);
    userEntity.setEmail("email");

    return userEntity;
  }

  private CompanyEntity createCompany() {
    CompanyEntity companyEntity = new CompanyEntity();
    companyEntity.setAddress("adress");
    companyEntity.setName(companyName);

    return companyEntity;
  }

}

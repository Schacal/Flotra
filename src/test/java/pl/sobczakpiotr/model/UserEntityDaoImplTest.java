package pl.sobczakpiotr.model;

import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */

public class UserEntityDaoImplTest extends DatabaseTest {

  private final int USER_ID = 10001;

  @Test
  public void shouldCreateAndReadNewUserInDatabase() {
    UserEntity user = createTestUser();
    userDao.createUser(user);
    UserEntity actualUser = userDao.findByUserID(USER_ID);
    Assert.assertEquals(USER_ID, actualUser.getId());
  }

  @Test
  public void userTableShouldBeEmptyOnInitialization() {
    List<UserEntity> allUsers = userDao.getAllUsers();
    Assert.assertTrue(allUsers.isEmpty());
  }

  @Test
  public void userShouldBeUpdated() {
    String updatedUserName = "Updated UserEntity Name";
    String updatedUserEmail = "Updated UserEntity Email";
    UserEntity user = createTestUser();
    userDao.createUser(user);
    UserEntity actualUser = userDao.findByUserID(USER_ID);

    actualUser.setUserName(updatedUserName);
    actualUser.setEmail(updatedUserEmail);
    userDao.updateUser(actualUser);

    UserEntity updatedUser = userDao.findByUserID(USER_ID);
    Assert.assertEquals(USER_ID, updatedUser.getId());
    Assert.assertEquals(updatedUserName, updatedUser.getUserName());
    Assert.assertEquals(updatedUserEmail, updatedUser.getEmail());
  }

  @Test
  public void existingUserShouldBeDeleted() {
    UserEntity user = createTestUser();
    userDao.createUser(user);
    UserEntity actualUser = userDao.findByUserID(USER_ID);
    userDao.deleteUser(actualUser);

    Assert.assertNull(userDao.findByUserID(USER_ID));
  }

  @Test
  public void unExistingUserShouldBeDeleted() {
    int userID = 123456;
    UserEntity userEntity = new UserEntity();
    userEntity.setId(userID);
    userEntity.setUserName("TestUserName");
    userEntity.setPassword("TestPassword");
    userEntity.setEmail("email@test.pl");
    userDao.deleteUser(userEntity);

    Assert.assertNull(userDao.findByUserID(userID));
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void shouldThrowExceptionIfManyUsersWithTheSameIdAreAddedToDB() {
    UserEntity user = createTestUser();
    userDao.createUser(user);
    userDao.createUser(user);

    Assert.fail();
  }

  @Test
  public void shouldFindUserByName() {
    String expectedUserName = "USER name";
    String expectedUserEmail = "USER name";
    String expectedPassword = "password";
    UserEntity user = new UserEntity();
    user.setId(USER_ID);
    user.setEmail(expectedUserEmail);
    user.setUserName(expectedUserName);
    user.setPassword(expectedPassword);

    userDao.createUser(user);
    Optional<UserEntity> actualUser = userDao.findUserByName(expectedUserName);
    if (actualUser.isPresent()) {
      UserEntity user2 = actualUser.get();
      Assert.assertEquals(USER_ID, user2.getId());
      Assert.assertEquals(expectedUserName, user2.getUserName());
      Assert.assertEquals(expectedUserEmail, user2.getEmail());
    } else {
      Assert.fail();
    }

  }

  @Test
  public void shouldFindUnexistingUserByName() {
    String expectedUserName = "USER name";

    Optional<UserEntity> actualUser = userDao.findUserByName(expectedUserName);

    Assert.assertTrue(!actualUser.isPresent());
  }

  private UserEntity createTestUser() {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(USER_ID);
    userEntity.setUserName("TestUserName");
    userEntity.setPassword("TestPassword");
    userEntity.setEmail("email@test.pl");
    return userEntity;
  }
}

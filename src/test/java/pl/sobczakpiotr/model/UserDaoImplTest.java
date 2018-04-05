package pl.sobczakpiotr.model;

import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */

public class UserDaoImplTest extends DatabaseTest {

  private final int USER_ID = 10001;

  @Test
  public void shouldCreateAndReadNewUserInDatabase() {
    User user = createTestUser();
    userDao.createUser(user);
    User actualUser = userDao.findByUserID(USER_ID);
    Assert.assertEquals(USER_ID, actualUser.getId());
  }

  @Test
  public void userTableShouldBeEmptyOnInitialization() {
    List<User> allUsers = userDao.getAllUsers();
    Assert.assertTrue(allUsers.isEmpty());
  }

  @Test
  public void userShouldBeUpdated() {
    String updatedUserName = "Updated User Name";
    String updatedUserEmail = "Updated User Email";
    User user = createTestUser();
    userDao.createUser(user);
    User actualUser = userDao.findByUserID(USER_ID);

    actualUser.setUserName(updatedUserName);
    actualUser.setEmail(updatedUserEmail);
    userDao.updateUser(actualUser);

    User updatedUser = userDao.findByUserID(USER_ID);
    Assert.assertEquals(USER_ID, updatedUser.getId());
    Assert.assertEquals(updatedUserName, updatedUser.getUserName());
    Assert.assertEquals(updatedUserEmail, updatedUser.getEmail());
  }

  @Test
  public void existingUserShouldBeDeleted() {
    User user = createTestUser();
    userDao.createUser(user);
    User actualUser = userDao.findByUserID(USER_ID);
    userDao.deleteUser(actualUser);

    Assert.assertNull(userDao.findByUserID(USER_ID));
  }

  @Test
  public void unExistingUserShouldBeDeleted() {
    int userID = 123456;
    User user = new User(userID, "a", "a", "a");
    userDao.deleteUser(user);

    Assert.assertNull(userDao.findByUserID(userID));
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void shouldThrowExceptionIfManyUsersWithTheSameIdAreAddedToDB() {
    User user = createTestUser();
    userDao.createUser(user);
    userDao.createUser(user);

    Assert.fail();
  }

  @Test
  public void shouldFindUserByName() {
    String expectedUserName = "USER name";
    String expectedUserEmail = "USER name";
    User user = new User(USER_ID, expectedUserName, "pass", expectedUserEmail);

    userDao.createUser(user);
    Optional<User> actualUser = userDao.findUserByName(expectedUserName);
    if (actualUser.isPresent()) {
      User user2 = actualUser.get();
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

    Optional<User> actualUser = userDao.findUserByName(expectedUserName);

    Assert.assertTrue(!actualUser.isPresent());
  }

  private User createTestUser() {
    return new User(USER_ID, "TestUserName", "TestPassword", "email@test.pl");
  }
}

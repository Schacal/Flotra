package pl.sobczakpiotr.model.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;
import org.junit.Test;
import pl.sobczakpiotr.model.DatabaseTest;

/**
 * @author Piotr Sobczak, created on 02-04-2018
 */

public class UserEntityDaoImplTest extends DatabaseTest {

  private final String userName = "userName";

  @Test
  public void shouldCreateAndReadNewUserInDatabase() {
    UserEntity user = createTestUser(userName);
    userDao.createUser(user);
    Optional<UserEntity> userOptional = userDao.findUserByName(userName);
    assertTrue(userOptional.isPresent());
    assertEquals(userName, userOptional.get().getUserName());
  }

  @Test
  public void userTableShouldBeEmptyOnInitialization() {
    List<UserEntity> allUsers = userDao.getAllUsers();
    assertTrue(allUsers.isEmpty());
  }

  @Test
  public void userShouldBeUpdated() {
    String updatedUserName = "Updated UserEntity Name";
    String updatedUserEmail = "Updated UserEntity Email";
    UserEntity user = createTestUser(userName);
    userDao.createUser(user);
    Optional<UserEntity> actualUser = userDao.findUserByName(userName);

    if (!actualUser.isPresent()) {
      fail();
    }
    UserEntity userEntity = actualUser.get();
    userEntity.setUserName(updatedUserName);
    userEntity.setEmail(updatedUserEmail);
    userDao.updateUser(userEntity);

    Optional<UserEntity> updatedUser = userDao.findUserByName(updatedUserName);

    if (!updatedUser.isPresent()) {
      fail();
    }
    UserEntity actualUser2 = updatedUser.get();
    assertEquals(updatedUserName, actualUser2.getUserName());
    assertEquals(updatedUserEmail, actualUser2.getEmail());
  }

  @Test
  public void existingUserShouldBeDeleted() {
    UserEntity user = createTestUser(userName);
    userDao.createUser(user);
    Optional<UserEntity> actualUser = userDao.findUserByName(userName);
    if (!actualUser.isPresent()) {
      fail();
    }
    userDao.deleteUser(actualUser.get());
    Optional<UserEntity> userByName = userDao.findUserByName(userName);
    assertFalse(userByName.isPresent());
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

    assertNull(userDao.findByUserID(userID));
  }

  @Test
  public void shouldFindUserByName() {
    String expectedUserName = "USER name";
    String expectedUserEmail = "USER name";
    String expectedPassword = "password";
    UserEntity user = new UserEntity();
    user.setEmail(expectedUserEmail);
    user.setUserName(expectedUserName);
    user.setPassword(expectedPassword);

    userDao.createUser(user);
    Optional<UserEntity> actualUser = userDao.findUserByName(expectedUserName);
    if (actualUser.isPresent()) {
      UserEntity user2 = actualUser.get();
      assertEquals(expectedUserName, user2.getUserName());
      assertEquals(expectedUserEmail, user2.getEmail());
    } else {
      fail();
    }

  }

  @Test
  public void shouldFindUnexistingUserByName() {
    String expectedUserName = "USER name";

    Optional<UserEntity> actualUser = userDao.findUserByName(expectedUserName);

    assertTrue(!actualUser.isPresent());
  }

  private UserEntity createTestUser(String userName) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(userName);
    userEntity.setPassword("TestPassword");
    userEntity.setEmail("email@test.pl");
    return userEntity;
  }
}

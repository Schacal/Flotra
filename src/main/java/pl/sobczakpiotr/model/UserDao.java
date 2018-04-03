package pl.sobczakpiotr.model;

import java.util.List;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */
public interface UserDao {

  User findByUserName(String userName);

  User findByUserEmail(String email);

  User findByUserID(long id);

  List<User> getAllUsers();

  void createUser(User user);

  void deleteUser(User user);

  void updateUser(User user);

}

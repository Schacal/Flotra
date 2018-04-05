package pl.sobczakpiotr.model;

import java.util.List;
import java.util.Optional;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */

public interface UserDao {

  User findByUserID(int id);

  List getAllUsers();

  void createUser(User user);

  void deleteUser(User user);

  void updateUser(User user);

  Optional<User> findUserByName(String name);

}

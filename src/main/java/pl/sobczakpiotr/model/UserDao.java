package pl.sobczakpiotr.model;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */

public interface UserDao {

  User findByUserName(String userName);

  User findByUserEmail(String email);

  User findByUserID(int id);

  List<User> getAllUsers();

  void createUser(User user);

  void deleteUser(User user);

  void updateUser(User user);

}

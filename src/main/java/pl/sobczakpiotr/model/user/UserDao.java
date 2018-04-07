package pl.sobczakpiotr.model.user;

import java.util.List;
import java.util.Optional;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */

public interface UserDao {

  UserEntity findByUserID(int id);

  List getAllUsers();

  void createUser(UserEntity user);

  void deleteUser(UserEntity user);

  void updateUser(UserEntity user);

  Optional<UserEntity> findUserByName(String name);

}

package pl.sobczakpiotr.model;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */
public interface UserDao {

  User findByUserName();

  User findByUserEmail();

  User findByUserID();

}

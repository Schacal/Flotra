package pl.sobczakpiotr.authentication;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sobczakpiotr.model.user.UserDao;
import pl.sobczakpiotr.model.user.UserEntity;

@Component
public class BasicAccessControl implements AccessControl {

  @Autowired
  private UserDao userDao;

  @Override
  public boolean signIn(String username, String password) {
    Optional<UserEntity> user = userDao.findUserByName(username);

    if (user.isPresent() &&
        user.get().getUserName().equals(username) &&
        user.get().getPassword().equals(password)) {
      CurrentUser.set(username);
      return true;
    }

    return false;
  }

  @Override
  public boolean isUserSignedIn() {
    return !CurrentUser.get().isEmpty();
  }

  @Override
  public boolean isUserInRole(String role) {
    if ("admin".equals(role)) {
      return getPrincipalName().equals("admin");
    }

    return true;
  }

  @Override
  public String getPrincipalName() {
    return CurrentUser.get();
  }

}

package pl.sobczakpiotr.authentication;


public class BasicAccessControl implements AccessControl {

  @Override
  public boolean signIn(String username, String password) {
    if (username == null || username.isEmpty()) {
      return false;
    }

    CurrentUser.set(username);
    return true;
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

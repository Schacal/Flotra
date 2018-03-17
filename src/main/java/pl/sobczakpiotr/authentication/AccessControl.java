package pl.sobczakpiotr.authentication;

public interface AccessControl {

  boolean signIn(String username, String password);

  boolean isUserSignedIn();

  boolean isUserInRole(String role);

  String getPrincipalName();
}

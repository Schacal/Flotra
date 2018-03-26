package pl.sobczakpiotr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */
@Entity
@Table(name = "USER")
public class User {

  @Id
  @Column(name = "ID")
  private int id;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "EMAIL")
  private String email;

  public User() {
  }

  public User(int id, String userName, String password, String email) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

package pl.sobczakpiotr.model.user;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.sobczakpiotr.model.car.CarEntity;

/**
 * @author Piotr Sobczak, created on 06-04-2018
 */
@Entity
@Table(name = "\"user\"", schema = "public")
public class UserEntity {

  private int id;
  private String userName;
  private String password;
  private String email;
  private Collection<CarEntity> carsById;


  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "user_name", nullable = false, length = 255)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Basic
  @Column(name = "password", nullable = false, length = 255)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "email", nullable = true, length = 255)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserEntity that = (UserEntity) o;
    return id == that.id &&
        Objects.equals(userName, that.userName) &&
        Objects.equals(password, that.password) &&
        Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, userName, password, email);
  }

  @OneToMany(mappedBy = "userByUserId")
  public Collection<CarEntity> getCarsById() {
    return carsById;
  }

  public void setCarsById(Collection<CarEntity> carsById) {
    this.carsById = carsById;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}

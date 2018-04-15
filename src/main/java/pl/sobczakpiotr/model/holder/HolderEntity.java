package pl.sobczakpiotr.model.holder;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pl.sobczakpiotr.model.car.CarEntity;

/**
 * @author Piotr Sobczak, created on 07-04-2018
 */
@Entity
@Table(name = "holder", schema = "public")
public class HolderEntity {

  private long peselNumber;
  private String firstname;
  private String surname;
  private Long telephone;
  private CarEntity carByCarId;

  @Id
  @Column(name = "pesel_number", nullable = false)
  public long getPeselNumber() {
    return peselNumber;
  }

  public void setPeselNumber(long peselNumber) {
    this.peselNumber = peselNumber;
  }

  @Basic
  @Column(name = "firstname", nullable = false, length = -1)
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  @Basic
  @Column(name = "surname", nullable = false, length = -1)
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Basic
  @Column(name = "telephone", nullable = true)
  public Long getTelephone() {
    return telephone;
  }

  public void setTelephone(Long telephone) {
    this.telephone = telephone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HolderEntity that = (HolderEntity) o;
    return peselNumber == that.peselNumber &&
        Objects.equals(firstname, that.firstname) &&
        Objects.equals(surname, that.surname) &&
        Objects.equals(telephone, that.telephone);
  }

  @Override
  public int hashCode() {

    return Objects.hash(peselNumber, firstname, surname, telephone);
  }

  @ManyToOne
  @JoinColumn(name = "car_id", referencedColumnName = "car_id", nullable = false)
  public CarEntity getCarByCarId() {
    return carByCarId;
  }

  public void setCarByCarId(CarEntity carByCarId) {
    this.carByCarId = carByCarId;
  }
}

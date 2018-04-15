package pl.sobczakpiotr.model;

import java.sql.Date;
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
@Table(name = "road_accidents", schema = "public")
public class RoadAccidentsEntity {

  private int id;
  private Date date;
  private String description;
  private HolderEntity holderByHolderId;
  private CarEntity carByCarId;

  @Id
  @Column(name = "id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "date", nullable = false)
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Basic
  @Column(name = "description", nullable = true, length = -1)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadAccidentsEntity that = (RoadAccidentsEntity) o;
    return id == that.id &&
        Objects.equals(date, that.date) &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, date, description);
  }

  @ManyToOne
  @JoinColumn(name = "holder_id", referencedColumnName = "pesel_number")
  public HolderEntity getHolderByHolderId() {
    return holderByHolderId;
  }

  public void setHolderByHolderId(HolderEntity holderByHolderId) {
    this.holderByHolderId = holderByHolderId;
  }

  @ManyToOne
  @JoinColumn(name = "car_id", referencedColumnName = "car_id")
  public CarEntity getCarByCarId() {
    return carByCarId;
  }

  public void setCarByCarId(CarEntity carByCarId) {
    this.carByCarId = carByCarId;
  }
}

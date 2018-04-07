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
@Table(name = "repair", schema = "public", catalog = "FlotraDatabase")
public class RepairEntity {

  private int repairId;
  private Date date;
  private String description;
  private CarEntity carByCarId;

  @Id
  @Column(name = "repair_id", nullable = false)
  public int getRepairId() {
    return repairId;
  }

  public void setRepairId(int repairId) {
    this.repairId = repairId;
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
    RepairEntity that = (RepairEntity) o;
    return repairId == that.repairId &&
        Objects.equals(date, that.date) &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(repairId, date, description);
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

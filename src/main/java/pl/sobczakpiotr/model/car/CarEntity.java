package pl.sobczakpiotr.model.car;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.sobczakpiotr.model.carDetails.CardetailsEntity;
import pl.sobczakpiotr.model.holder.HolderEntity;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 06-04-2018
 */
@Entity
@Table(name = "car", schema = "public")
public class CarEntity {

  private int carId;
  private String licensePlateNumber;
  private UserEntity userByUserId;
  private Long vinNumber;
  private Date insuranceStartDate;
  private Date insuranceEndDate;
  private Date technicalExaminationEndDate;
  private CardetailsEntity carDetailsEntity;
  private List<HolderEntity> holders;

  @Id
  @Column(name = "car_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getCarId() {
    return carId;
  }

  public void setCarId(int carId) {
    this.carId = carId;
  }

  @Basic
  @Column(name = "license_plate_number", nullable = true, length = 255)
  public String getLicensePlateNumber() {
    return licensePlateNumber;
  }

  public void setLicensePlateNumber(String licensePlateNumber) {
    this.licensePlateNumber = licensePlateNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CarEntity carEntity = (CarEntity) o;
    return carId == carEntity.carId &&
        Objects.equals(licensePlateNumber, carEntity.licensePlateNumber);
  }

  @Override
  public int hashCode() {

    return Objects.hash(carId, licensePlateNumber);
  }

  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
      CascadeType.REFRESH})
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  public UserEntity getUserByUserId() {
    return userByUserId;
  }

  public void setUserByUserId(UserEntity userByUserId) {
    this.userByUserId = userByUserId;
  }

  @Basic
  @Column(name = "vin_number", nullable = true)
  public Long getVinNumber() {
    return vinNumber;
  }

  public void setVinNumber(Long vinNumber) {
    this.vinNumber = vinNumber;
  }

  @Basic
  @Column(name = "insurance_start_date", nullable = true)
  public Date getInsuranceStartDate() {
    return insuranceStartDate;
  }

  public void setInsuranceStartDate(Date insuranceStartDate) {
    this.insuranceStartDate = insuranceStartDate;
  }

  @Basic
  @Column(name = "insurance_end_date", nullable = true)
  public Date getInsuranceEndDate() {
    return insuranceEndDate;
  }

  public void setInsuranceEndDate(Date insuranceEndDate) {
    this.insuranceEndDate = insuranceEndDate;
  }

  @Basic
  @Column(name = "technical_examination_end_date", nullable = true)
  public Date getTechnicalExaminationEndDate() {
    return technicalExaminationEndDate;
  }

  public void setTechnicalExaminationEndDate(Date technicalExaminationEndDate) {
    this.technicalExaminationEndDate = technicalExaminationEndDate;
  }

  @Override
  public String toString() {
    return "CarEntity{" +
        "carId=" + carId +
        ", licensePlateNumber='" + licensePlateNumber + '\'' +
        ", userByUserId=" + userByUserId +
        ", vinNumber=" + vinNumber +
        ", insuranceStartDate=" + insuranceStartDate +
        ", insuranceEndDate=" + insuranceEndDate +
        ", technicalExaminationEndDate=" + technicalExaminationEndDate +
        '}';
  }

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "carByCarId", cascade = CascadeType.ALL)
  public CardetailsEntity getCarDetailsEntity() {
    return carDetailsEntity;
  }

  public void setCarDetailsEntity(CardetailsEntity carDetailsEntity) {
    this.carDetailsEntity = carDetailsEntity;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "carByCarId")
  public List<HolderEntity> getHolders() {
    return holders;
  }

  public void setHolders(List<HolderEntity> holders) {
    this.holders = holders;
  }
}

package pl.sobczakpiotr.model;

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
@Table(name = "cardetails", schema = "public", catalog = "FlotraDatabase")
public class CardetailsEntity {

  private int carDetailId;
  private String manufacturer;
  private String model;
  private String engine;
  private String color;
  private String fuelType;
  private Integer enginePower;
  private Integer doorsNumber;
  private Integer mileage;
  private String equipment;
  private CarEntity carByCarId;

  @Id
  @Column(name = "car_detail_id", nullable = false)
  public int getCarDetailId() {
    return carDetailId;
  }

  public void setCarDetailId(int carDetailId) {
    this.carDetailId = carDetailId;
  }

  @Basic
  @Column(name = "manufacturer", nullable = true, length = 255)
  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Basic
  @Column(name = "model", nullable = true, length = 255)
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Basic
  @Column(name = "engine", nullable = true, length = 255)
  public String getEngine() {
    return engine;
  }

  public void setEngine(String engine) {
    this.engine = engine;
  }

  @Basic
  @Column(name = "color", nullable = true, length = 255)
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Basic
  @Column(name = "fuel_type", nullable = true, length = 255)
  public String getFuelType() {
    return fuelType;
  }

  public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
  }

  @Basic
  @Column(name = "engine_power", nullable = true)
  public Integer getEnginePower() {
    return enginePower;
  }

  public void setEnginePower(Integer enginePower) {
    this.enginePower = enginePower;
  }

  @Basic
  @Column(name = "doors_number", nullable = true)
  public Integer getDoorsNumber() {
    return doorsNumber;
  }

  public void setDoorsNumber(Integer doorsNumber) {
    this.doorsNumber = doorsNumber;
  }

  @Basic
  @Column(name = "mileage", nullable = true)
  public Integer getMileage() {
    return mileage;
  }

  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }

  @Basic
  @Column(name = "equipment", nullable = true, length = -1)
  public String getEquipment() {
    return equipment;
  }

  public void setEquipment(String equipment) {
    this.equipment = equipment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardetailsEntity that = (CardetailsEntity) o;
    return carDetailId == that.carDetailId &&
        Objects.equals(manufacturer, that.manufacturer) &&
        Objects.equals(model, that.model) &&
        Objects.equals(engine, that.engine) &&
        Objects.equals(color, that.color) &&
        Objects.equals(fuelType, that.fuelType) &&
        Objects.equals(enginePower, that.enginePower) &&
        Objects.equals(doorsNumber, that.doorsNumber) &&
        Objects.equals(mileage, that.mileage) &&
        Objects.equals(equipment, that.equipment);
  }

  @Override
  public int hashCode() {

    return Objects
        .hash(carDetailId, manufacturer, model, engine, color, fuelType, enginePower, doorsNumber, mileage, equipment);
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

package pl.sobczakpiotr.model.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;
import org.junit.Test;
import pl.sobczakpiotr.model.DatabaseTest;
import pl.sobczakpiotr.model.carDetails.CardetailsEntity;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 07-04-2018
 */
public class CarDaoImplTest extends DatabaseTest {

  private final String USER_NAME = "testUserName";
  private final String EMAIL = "email@test.com";
  private final String CAR_COLOR = "BLACK";

  @Test
  public void shouldCreateCarEntity() {
    CarEntity carEntity = createCarEntity(UNIQUE_VALUE);
    UserEntity userEntity = createUserEntity(USER_NAME, UNIQUE_VALUE);

    carDao.createCar(carEntity);
    List<CarEntity> allCarsForUser = carDao.getAllCarsForUser(userEntity);
    assertFalse(allCarsForUser.isEmpty());
    assertEquals(1, allCarsForUser.size());
  }

  @Test
  public void shouldDeleteCarEntity() {
    CarEntity carEntity = createCarEntity(UNIQUE_VALUE);
    UserEntity userEntity = createUserEntity(USER_NAME, UNIQUE_VALUE);
    carDao.createCar(carEntity);
    carDao.deleteCar(carEntity);
    List<CarEntity> allCarsForUser = carDao.getAllCarsForUser(userEntity);
    assertTrue(allCarsForUser.isEmpty());

  }

  @Test
  public void shouldUpdateCarEntity() {
    long vinNumber = 222222L;
    CarEntity carEntity = createCarEntity(UNIQUE_VALUE);
    UserEntity userEntity = createUserEntity(USER_NAME, UNIQUE_VALUE);
    carDao.createCar(carEntity);
    carEntity.setVinNumber(vinNumber);
    carDao.updateCar(carEntity);

    List<CarEntity> allCarsForUser = carDao.getAllCarsForUser(userEntity);
    assertFalse(allCarsForUser.isEmpty());
    assertEquals(1, allCarsForUser.size());
    CarEntity updatedCarEntity = allCarsForUser.iterator().next();
    assertEquals(new Long(vinNumber), updatedCarEntity.getVinNumber());

  }

  @Test
  public void shouldCreateCarEntityDetails() {
    CarEntity carEntity = createCarEntity(UNIQUE_VALUE);
    UserEntity userEntity = createUserEntity(USER_NAME, UNIQUE_VALUE);
    carEntity.setCarDetailsEntity(createCardetailsEntity(carEntity));

    carDao.createCar(carEntity);
    List<CarEntity> allCarsForUser = carDao.getAllCarsForUser(userEntity);
    assertFalse(allCarsForUser.isEmpty());
    assertEquals(1, allCarsForUser.size());
    CarEntity carEntityFromDb = allCarsForUser.iterator().next();
    assertEquals(CAR_COLOR, carEntityFromDb.getCarDetailsEntity().getColor());
  }


  private CarEntity createCarEntity(String unique) {
    CarEntity carEntity = new CarEntity();
    carEntity.setUserByUserId(createUserEntity(USER_NAME, unique));
    carEntity.setInsuranceEndDate(new Date(10000));
    carEntity.setInsuranceStartDate(new Date(100));
    carEntity.setLicensePlateNumber("DW 233" + unique);
    carEntity.setTechnicalExaminationEndDate(new Date(20000));
    carEntity.setVinNumber(1234234L);
    return carEntity;
  }

  private UserEntity createUserEntity(String userName, String unique) {
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(EMAIL + unique);
    userEntity.setUserName(userName);
    userEntity.setPassword("password" + unique);
    return userEntity;
  }

  private CardetailsEntity createCardetailsEntity(CarEntity carEntity) {
    CardetailsEntity cardetailsEntity = new CardetailsEntity();
    cardetailsEntity.setCarByCarId(carEntity);
    cardetailsEntity.setColor(CAR_COLOR);
    cardetailsEntity.setDoorsNumber(5);
    cardetailsEntity.setEngine("Engine");
    cardetailsEntity.setEquipment("Air condition");

    return cardetailsEntity;
  }

}
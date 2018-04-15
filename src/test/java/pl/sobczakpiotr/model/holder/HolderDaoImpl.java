package pl.sobczakpiotr.model.holder;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.sobczakpiotr.model.DatabaseTest;
import pl.sobczakpiotr.model.car.CarEntity;
import pl.sobczakpiotr.model.carDetails.CardetailsEntity;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 15-04-2018
 */
public class HolderDaoImpl extends DatabaseTest {

  private final String companyName = "Company Name";
  private final String USER_NAME = "testUserName";
  private final String EMAIL = "email@test.com";
  private final String CAR_COLOR = "BLACK";

  @Test
  public void shouldCreateHolderForCar() {
    CarEntity carEntity = createCarEntity(UNIQUE_VALUE);
    UserEntity userEntity = createUserEntity(USER_NAME, UNIQUE_VALUE);
    HolderEntity firstName = createHolder("FirstName");
    HolderEntity firstName2 = createHolder("FirstName2");
    carEntity.setCarDetailsEntity(createCardetailsEntity(carEntity));
    carEntity.setHolders(Arrays.asList(firstName, firstName2));

    carDao.createCar(carEntity);
    List<CarEntity> allCarsForUser = carDao.getAllCarsForUser(userEntity);
    Assert.assertFalse(allCarsForUser.isEmpty());
    CarEntity next = allCarsForUser.iterator().next();

    List<HolderEntity> holders = carEntity.getHolders();

    Assert.assertFalse(holders.isEmpty());
    Assert.assertEquals(2, holders.size());
    HolderEntity holder = holders.iterator().next();
    Assert.assertEquals("FirstName", holder.getFirstname());

  }

  private CarEntity createCarEntity(String unique) {
    CarEntity carEntity = new CarEntity();
    carEntity.setUserByUserId(createUserEntity(USER_NAME, unique));
    carEntity.setInsuranceEndDate(new Date(10000));
    carEntity.setInsuranceStartDate(new Date(100));
    carEntity.setLicensePlateNumber("DW 455" + unique);
    carEntity.setTechnicalExaminationEndDate(new Date(20000));
    carEntity.setVinNumber(1234234L);
    return carEntity;
  }

  private UserEntity createUserEntity(String userName, String unique) {
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(EMAIL + unique);
    userEntity.setUserName(userName);
    userEntity.setPassword("pass" + unique);
    return userEntity;
  }

  private CardetailsEntity createCardetailsEntity(CarEntity carEntity) {
    CardetailsEntity cardetailsEntity = new CardetailsEntity();
    cardetailsEntity.setCarByCarId(carEntity);
    cardetailsEntity.setColor(CAR_COLOR);
    cardetailsEntity.setDoorsNumber(5);
    cardetailsEntity.setEngine("Engine2");
    cardetailsEntity.setEquipment("Air condition");

    return cardetailsEntity;
  }

  private HolderEntity createHolder(String firstName) {
    HolderEntity holderEntity = new HolderEntity();
    holderEntity.setFirstname(firstName);
    holderEntity.setSurname("Surname");
    holderEntity.setTelephone(1234L);

    return holderEntity;
  }


}

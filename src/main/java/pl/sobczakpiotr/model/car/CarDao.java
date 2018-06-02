package pl.sobczakpiotr.model.car;

import java.util.List;
import java.util.Optional;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 07-04-2018
 */
public interface CarDao {

  List<CarEntity> getAllCarsForUser(UserEntity userEntity);

  void createCar(CarEntity carEntity);

  void deleteCar(CarEntity carEntity);

  void updateCar(CarEntity carEntity);

  Optional<CarEntity> findCar(int carId);
}

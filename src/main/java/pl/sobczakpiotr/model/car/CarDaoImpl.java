package pl.sobczakpiotr.model.car;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 07-04-2018
 */
@Repository
@Transactional
public class CarDaoImpl implements CarDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<CarEntity> getAllCarsForUser(UserEntity userEntity) {
    return null;
  }

  @Override
  public void createCar(CarEntity carEntity) {

  }

  @Override
  public void deleteCar(CarEntity carEntity) {

  }

  @Override
  public void updateCar(CarEntity carEntity) {

  }
}

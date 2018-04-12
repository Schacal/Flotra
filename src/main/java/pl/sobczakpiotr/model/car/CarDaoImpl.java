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
    List<CarEntity> resultList = entityManager
        .createQuery("SELECT t FROM CarEntity t where t.userByUserId.userName = :value1", CarEntity.class)
        .setParameter("value1", userEntity.getUserName()).getResultList();
    return resultList;
  }

  @Override
  public void createCar(CarEntity carEntity) {
    entityManager.persist(carEntity);
  }

  @Override
  public void deleteCar(CarEntity carEntity) {
    if (entityManager.contains(carEntity)) {
      entityManager.remove(carEntity);
    } else {
      entityManager.remove(entityManager.merge(carEntity));
    }
  }

  @Override
  public void updateCar(CarEntity carEntity) {
    entityManager.merge(carEntity);
  }
}

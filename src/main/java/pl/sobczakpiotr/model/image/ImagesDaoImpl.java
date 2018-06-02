package pl.sobczakpiotr.model.image;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sobczakpiotr.model.carDetails.CardetailsEntity;

/**
 * @author Piotr Sobczak, created on 29-05-2018
 */
@Repository
@Transactional
public class ImagesDaoImpl implements ImagesDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Optional<ImagesEntity> getImage(CardetailsEntity cardetailsEntity) {
    try {
      ImagesEntity image = entityManager
          .createQuery("SELECT t FROM ImagesEntity t where t.cardetailsByCardetailsid = :value1", ImagesEntity.class)
          .setParameter("value1", cardetailsEntity).getSingleResult();
      return Optional.of(image);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}

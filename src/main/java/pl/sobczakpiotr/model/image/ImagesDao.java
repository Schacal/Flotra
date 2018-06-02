package pl.sobczakpiotr.model.image;

import java.util.Optional;
import pl.sobczakpiotr.model.carDetails.CardetailsEntity;

/**
 * @author Piotr Sobczak, created on 29-05-2018
 */
public interface ImagesDao {

  Optional<ImagesEntity> getImage(CardetailsEntity cardetailsEntity);

}

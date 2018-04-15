package pl.sobczakpiotr.model;

import java.sql.Time;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pl.sobczakpiotr.model.carDetails.CardetailsEntity;

/**
 * @author Piotr Sobczak, created on 07-04-2018
 */
@Entity
@Table(name = "images", schema = "public")
public class ImagesEntity {

  private int imageId;
  private Time date;
  private byte[] image;
  private CardetailsEntity cardetailsByCardetailsid;

  @Id
  @Column(name = "image_id", nullable = false)
  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  @Basic
  @Column(name = "date", nullable = true)
  public Time getDate() {
    return date;
  }

  public void setDate(Time date) {
    this.date = date;
  }

  @Basic
  @Column(name = "image", nullable = true)
  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImagesEntity that = (ImagesEntity) o;
    return imageId == that.imageId &&
        Objects.equals(date, that.date) &&
        Arrays.equals(image, that.image);
  }

  @Override
  public int hashCode() {

    int result = Objects.hash(imageId, date);
    result = 31 * result + Arrays.hashCode(image);
    return result;
  }

  @ManyToOne
  @JoinColumn(name = "cardetailsid", referencedColumnName = "car_detail_id", nullable = false)
  public CardetailsEntity getCardetailsByCardetailsid() {
    return cardetailsByCardetailsid;
  }

  public void setCardetailsByCardetailsid(CardetailsEntity cardetailsByCardetailsid) {
    this.cardetailsByCardetailsid = cardetailsByCardetailsid;
  }
}

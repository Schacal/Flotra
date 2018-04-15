package pl.sobczakpiotr.model.company;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.sobczakpiotr.model.user.UserEntity;

/**
 * @author Piotr Sobczak, created on 07-04-2018
 */
@Entity
@Table(name = "company", schema = "public")
public class CompanyEntity {

  private int companyId;
  private String name;
  private String website;
  private String address;
  private Long telephone;
  private UserEntity userEntity;

  @Id
  @Column(name = "company_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getCompanyId() {
    return companyId;
  }

  public void setCompanyId(int companyId) {
    this.companyId = companyId;
  }

  @Basic
  @Column(name = "companyName", nullable = false, length = -1)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "website", nullable = true, length = -1)
  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  @Basic
  @Column(name = "address", nullable = true, length = -1)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Basic
  @Column(name = "telephone", nullable = true)
  public Long getTelephone() {
    return telephone;
  }

  public void setTelephone(Long telephone) {
    this.telephone = telephone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompanyEntity that = (CompanyEntity) o;
    return companyId == that.companyId &&
        Objects.equals(name, that.name) &&
        Objects.equals(website, that.website) &&
        Objects.equals(address, that.address) &&
        Objects.equals(telephone, that.telephone);
  }

  @Override
  public int hashCode() {

    return Objects.hash(companyId, name, website, address, telephone);
  }

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }
}

package pl.sobczakpiotr.model.user;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

  @PersistenceContext
  private EntityManager entityManager;


  public void createUser(UserEntity user) {
    entityManager.persist(user);
  }

  public void deleteUser(UserEntity user) {
    if (entityManager.contains(user)) {
      entityManager.remove(user);
    } else {
      entityManager.remove(entityManager.merge(user));
    }
  }

  @Override
  public UserEntity findByUserID(int id) {
    return entityManager.find(UserEntity.class, id);
  }

  @Override
  public List<UserEntity> getAllUsers() {
    Query from_user = entityManager.createQuery("from UserEntity ");
    return from_user.getResultList();
  }

  @Override
  public void updateUser(UserEntity user) {
    entityManager.merge(user);
  }

  @Override
  public Optional<UserEntity> findUserByName(String name) {
    try {
      UserEntity user = entityManager
          .createQuery("SELECT t FROM UserEntity t where t.userName = :value1", UserEntity.class)
          .setParameter("value1", name).getSingleResult();
      return Optional.of(user);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}

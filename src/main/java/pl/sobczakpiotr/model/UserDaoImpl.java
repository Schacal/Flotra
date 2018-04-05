package pl.sobczakpiotr.model;

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


  public void createUser(User user) {
    entityManager.persist(user);
  }

  public void deleteUser(User user) {
    if (entityManager.contains(user)) {
      entityManager.remove(user);
    } else {
      entityManager.remove(entityManager.merge(user));
    }
  }

  @Override
  public User findByUserID(int id) {
    return entityManager.find(User.class, id);
  }

  @Override
  public List<User> getAllUsers() {
    Query from_user = entityManager.createQuery("from User");
    return from_user.getResultList();
  }

  @Override
  public void updateUser(User user) {
    entityManager.merge(user);
  }

  @Override
  public Optional<User> findUserByName(String name) {
    try {
      User user = entityManager.createQuery("SELECT t FROM User t where t.userName = :value1", User.class)
          .setParameter("value1", name).getSingleResult();
      return Optional.of(user);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}

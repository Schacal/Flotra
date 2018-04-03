package pl.sobczakpiotr.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */
@Repository
public class UserDaoImpl implements UserDao{

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

  public List getAll() {
    return entityManager.createQuery("from User").getResultList();
  }

  public void update(User user) {
    entityManager.merge(user);
  }

  @Override
  public User findByUserName(String userName) {
    return null;
  }

  @Override
  public User findByUserEmail(String email) {
    return null;
  }

  @Override
  public User findByUserID(long id) {
    return null;
  }

  @Override
  public List<User> getAllUsers() {
    return null;
  }

  @Override
  public void updateUser(User user) {

  }
}

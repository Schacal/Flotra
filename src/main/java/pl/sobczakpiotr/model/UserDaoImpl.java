package pl.sobczakpiotr.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Piotr Sobczak, created on 19-03-2018
 */
@Repository
public class UserDaoImpl {

  @Autowired
  private SessionFactory sessionFactory;

}

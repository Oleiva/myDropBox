package io.github.oleiva.myDropBox.users.authentication;

import io.github.oleiva.myDropBox.users.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UserLoginAttemptsRepository {
  @Autowired
  private SessionFactory sessionFactory;

  public Serializable create(UserLoginAttemptsEntity attemptsEntity) {
    return getSession().save(attemptsEntity);
  }

  public UserLoginAttemptsEntity read(Long id) {
    return (UserLoginAttemptsEntity) getSession().load(UserLoginAttemptsEntity.class, id);
  }

  public UserLoginAttemptsEntity findByUser(UsersEntity usersEntity) {
    Criteria criteria = getSession().createCriteria(UserLoginAttemptsEntity.class);
    criteria.add(Restrictions.eq("user", usersEntity));
    return (UserLoginAttemptsEntity) criteria.uniqueResult();
  }

  public void update(UserLoginAttemptsEntity attemptsEntity) {
    getSession().update(attemptsEntity);
    getSession().flush();
  }

  public void delete(UserLoginAttemptsEntity attemptsEntity) {
    getSession().delete(attemptsEntity);
    getSession().flush();
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

}

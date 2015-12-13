package me.box.users;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;

@Repository
public class UserRepository {

  @Autowired
  private SessionFactory sessionFactory;

  public Serializable create(UsersEntity user) {
    return getSession().save(user);
  }

  public UsersEntity read(Long id) {
    return (UsersEntity) getSession().load(UsersEntity.class, id);
  }

  public void update(UsersEntity user) {
    getSession().update(user);
    getSession().flush();
  }

  public Collection<UsersEntity> findAll() {
    return getSession().createCriteria(UsersEntity.class).list();
  }

  public void delete(UsersEntity user) {
    getSession().delete(user);
  }

  public void deleteById(Long id) {
    getSession().delete(read(id));
  }

  public UsersEntity findByUsername(String username) {
    Criteria criteria = getSession().createCriteria(UsersEntity.class);
    criteria.add(Restrictions.eq("username", username));
    return (UsersEntity) criteria.uniqueResult();
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}

package io.github.oleiva.myDropBox.files;

import io.github.oleiva.myDropBox.entity.FilesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class FileRepository {

  @Autowired
  private SessionFactory sessionFactory;

  public Serializable create(FilesEntity filesEntity) {
    return getSession().save(filesEntity);
  }

  public FilesEntity read(Long id) {
    return (FilesEntity) getSession().load(FilesEntity.class, id);
  }

  public void update(FilesEntity filesEntity) {
    getSession().update(filesEntity);
    getSession().flush();
  }

  public void delete(FilesEntity filesEntity) {
    getSession().delete(filesEntity);
    getSession().flush();
  }

  public void deleteById(Long id) {
    delete(read(id));
  }

  public List<FilesEntity> findAll() {
    return getSession().createCriteria(FilesEntity.class).list();
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}

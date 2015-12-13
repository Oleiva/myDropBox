package io.github.oleiva.myDropBox.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "files")
public class FilesEntity implements Serializable {

  private static final long serialVersionUID = -1690779917824373282L;

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "author")
  private UsersEntity author;

  @Column(name = "name", length = 255, nullable = false, unique = true)
  private String name;

  @Column(name = "creation_date", insertable = false, updatable = false)
  private Timestamp creationDate;

  @Column(name = "modification_date", insertable = false, updatable = false)
  private Timestamp modificationDate;

  @Column(name = "view_list", nullable = false)
  private String viewList;

  @Column(name = "edit_list", nullable = false)
  private String editList;

  @Column(name = "remove_list", nullable = false)
  private String removeList;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UsersEntity getAuthor() {
    return author;
  }

  public void setAuthor(UsersEntity author) {
    this.author = author;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getViewList() {
    return viewList;
  }

  public void setViewList(String viewList) {
    this.viewList = viewList;
  }

  public String getEditList() {
    return editList;
  }

  public void setEditList(String editList) {
    this.editList = editList;
  }

  public String getRemoveList() {
    return removeList;
  }

  public void setRemoveList(String removeList) {
    this.removeList = removeList;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  public Timestamp getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(Timestamp modificationDate) {
    this.modificationDate = modificationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FilesEntity that = (FilesEntity) o;

    if (id != that.id) return false;
    if (author != null ? !author.equals(that.author) : that.author != null) return false;
    if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
    if (editList != null ? !editList.equals(that.editList) : that.editList != null) return false;
    if (modificationDate != null ? !modificationDate.equals(that.modificationDate) : that.modificationDate != null)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (removeList != null ? !removeList.equals(that.removeList) : that.removeList != null) return false;
    if (viewList != null ? !viewList.equals(that.viewList) : that.viewList != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id.intValue();
    result = 31 * result + (author != null ? author.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (viewList != null ? viewList.hashCode() : 0);
    result = 31 * result + (editList != null ? editList.hashCode() : 0);
    result = 31 * result + (removeList != null ? removeList.hashCode() : 0);
    result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
    result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
    return result;
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("author", author)
        .append("name", name)
        .append("viewList", viewList)
        .append("editList", editList)
        .append("removeList", removeList)
        .append("creationDate", creationDate)
        .append("modificationDate", modificationDate)
        .toString();
  }
}

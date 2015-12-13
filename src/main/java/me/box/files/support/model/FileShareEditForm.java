package me.box.files.support.model;

import me.box.files.FilesEntity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FileShareEditForm {

  @Pattern(regexp = "(\\d+)(,\\s*\\d+)*", message = "must by csv, with at least 1 value")
  @Size(max = 1024)
  private String viewList;

  @Pattern(regexp = "(\\d+)(,\\s*\\d+)*", message = "must by csv, with at least 1 value")
  @Size(max = 1024)
  private String editList;

  @Pattern(regexp = "(\\d+)(,\\s*\\d+)*", message = "must by csv, with at least 1 value")
  @Size(max = 1024)
  private String removeList;

  public FileShareEditForm() {
  }

  public FileShareEditForm(FilesEntity filesEntity) {
    this.viewList = filesEntity.getViewList();
    this.editList = filesEntity.getEditList();
    this.removeList = filesEntity.getRemoveList();
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
}

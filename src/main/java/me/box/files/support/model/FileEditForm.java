package me.box.files.support.model;

import me.box.files.FilesEntity;

import javax.validation.constraints.Size;

public class FileEditForm {

  @Size(min = 3, max = 255)
  private String filename;

  public FileEditForm() {
  }

  public FileEditForm(FilesEntity filesEntity) {
    this.filename = filesEntity.getName();
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }
}

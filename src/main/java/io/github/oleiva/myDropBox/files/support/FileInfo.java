package io.github.oleiva.myDropBox.files.support;

import io.github.oleiva.myDropBox.entity.FilesEntity;

/**
 * Created by Ivasoft on 9/27/14.
 */
public class FileInfo {
  private final Long length;
  private final String mimeType;
  private final FilesEntity filesEntity;

  public FileInfo(Long length, String mimeType, FilesEntity filesEntity) {
    this.length = length;
    this.mimeType = mimeType;
    this.filesEntity = filesEntity;
  }

  public Long getLength() {
    return length;
  }

  public String getMimeType() {
    return mimeType;
  }

  public FilesEntity getFilesEntity() {
    return filesEntity;
  }
}

package io.github.oleiva.myDropBox.files.support.model;

import io.github.oleiva.myDropBox.entity.FilesEntity;
import io.github.oleiva.myDropBox.files.support.FileInfo;

public class FileDownloadInfo {
  private final String name;
  private final int length;
  private final String mimeType;

  public FileDownloadInfo(FilesEntity filesEntity, FileInfo fileInfo) {
    this.name = filesEntity.getName();
    this.length = fileInfo.getLength().intValue();
    this.mimeType = fileInfo.getMimeType();
  }

  public String getName() {
    return name;
  }

  public int getLength() {
    return length;
  }

  public String getMimeType() {
    return mimeType;
  }

}

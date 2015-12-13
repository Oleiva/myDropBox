package io.github.oleiva.myDropBox.files.support;

import io.github.oleiva.myDropBox.files.FilesEntity;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

@Component
public class FileSaver {

  private static final String UPLOAD_DIR = "/Users/nivenoct/Developer/temp/box/upload";

  public boolean save(CommonsMultipartFile multipartFile, FilesEntity filesEntity) {
    File file = new File(getPathForFile(filesEntity));
    try {
      multipartFile.transferTo(file);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  public boolean remove(FilesEntity filesEntity) {
    File file = new File(getPathForFile(filesEntity));
    return file.delete();
  }

  public void transfer(FilesEntity filesEntity, OutputStream outputStream) {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(new File(getPathForFile(filesEntity)));
      IOUtils.copy(inputStream, outputStream);
    } catch (IOException ignored) {

    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }

  }

  public FileInfo getFilePlainInfo(FilesEntity filesEntity) {
    String path = getPathForFile(filesEntity);
    File file = new File(path);

    // get mimeType
    Tika tika = new Tika();
    String mimeType;
    try {
      mimeType = tika.detect(file);
    } catch (IOException e) {
      mimeType = "";
    }

    return new FileInfo(file.length(), mimeType, filesEntity);
  }

  public String getPathForFile(FilesEntity filesEntity) {
    return UPLOAD_DIR + "/" + filesEntity.getId().toString();
  }
}

package me.box.files.support.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Ivasoft on 9/8/14.
 */
public class FileUploadForm implements Serializable {

  private static final long serialVersionUID = 1866004896302341744L;

  @NotNull
  private CommonsMultipartFile multipartFile;

  public CommonsMultipartFile getMultipartFile() {
    return multipartFile;
  }

  public void setMultipartFile(CommonsMultipartFile multipartFile) {
    this.multipartFile = multipartFile;
  }
}

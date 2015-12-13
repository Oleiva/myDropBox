package me.box.support;

import me.box.files.support.model.FileUploadForm;
import org.springframework.web.bind.annotation.ModelAttribute;

public class UploadableController {

  @ModelAttribute("uploadForm")
  public FileUploadForm uploadForm() {
    return new FileUploadForm();
  }
}

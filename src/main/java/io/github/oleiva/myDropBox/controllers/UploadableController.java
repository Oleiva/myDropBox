package io.github.oleiva.myDropBox.controllers;

import io.github.oleiva.myDropBox.files.support.model.FileUploadForm;
import org.springframework.web.bind.annotation.ModelAttribute;

public class UploadableController {

  @ModelAttribute("uploadForm")
  public FileUploadForm uploadForm() {
    return new FileUploadForm();
  }
}

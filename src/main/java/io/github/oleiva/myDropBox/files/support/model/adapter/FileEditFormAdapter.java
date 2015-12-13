package io.github.oleiva.myDropBox.files.support.model.adapter;

import io.github.oleiva.myDropBox.entity.FilesEntity;
import io.github.oleiva.myDropBox.files.support.model.FileEditForm;
import org.springframework.stereotype.Service;

@Service
public class FileEditFormAdapter {
  public void updateFileEntityFromFileEditForm(FilesEntity filesEntity, FileEditForm fileEditForm) {
    filesEntity.setName(fileEditForm.getFilename());
  }
}

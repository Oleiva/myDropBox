package me.box.files.support.model.adapter;

import me.box.files.FilesEntity;
import me.box.files.support.model.FileEditForm;
import org.springframework.stereotype.Service;

@Service
public class FileEditFormAdapter {
  public void updateFileEntityFromFileEditForm(FilesEntity filesEntity, FileEditForm fileEditForm) {
    filesEntity.setName(fileEditForm.getFilename());
  }
}

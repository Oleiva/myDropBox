package io.github.oleiva.myDropBox.files.support.model.adapter;

import io.github.oleiva.myDropBox.entity.FilesEntity;
import io.github.oleiva.myDropBox.files.support.model.FileShareEditForm;
import org.springframework.stereotype.Service;

@Service
public class FileShareEditFormAdapter {
  public void updateFileEntityFromFileShareEditForm(FilesEntity filesEntity, FileShareEditForm fileShareEditForm) {
    filesEntity.setViewList(fileShareEditForm.getViewList());
    filesEntity.setEditList(fileShareEditForm.getEditList());
    filesEntity.setRemoveList(fileShareEditForm.getRemoveList());
  }
}

package me.box.files.support.model.adapter;

import me.box.files.FilesEntity;
import me.box.files.support.model.FileShareEditForm;
import org.springframework.stereotype.Service;

@Service
public class FileShareEditFormAdapter {
  public void updateFileEntityFromFileShareEditForm(FilesEntity filesEntity, FileShareEditForm fileShareEditForm) {
    filesEntity.setViewList(fileShareEditForm.getViewList());
    filesEntity.setEditList(fileShareEditForm.getEditList());
    filesEntity.setRemoveList(fileShareEditForm.getRemoveList());
  }
}

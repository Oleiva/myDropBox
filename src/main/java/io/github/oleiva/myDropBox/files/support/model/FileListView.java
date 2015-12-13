package io.github.oleiva.myDropBox.files.support.model;

import io.github.oleiva.myDropBox.files.FilesEntity;
import io.github.oleiva.myDropBox.files.support.FileInfo;
import org.apache.commons.io.FilenameUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FileListView implements Serializable {

  private static final long serialVersionUID = -1857022580095526848L;
  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
  private final Long id;
  private final String name;
  private final String author;
  private final String size;
  private final String extension;
  private final String modificationDate;
  private final boolean viewable;
  private final boolean editable;
  private final boolean removable;

  public FileListView(FileInfo fileInfo, Long id) {
    String userId = id.toString();
    FilesEntity fileEntity = fileInfo.getFilesEntity();
    this.id = fileEntity.getId();
    this.name = fileEntity.getName();
    this.author = fileEntity.getAuthor().getUsername();
    this.size = readableFileSize(fileInfo.getLength());
    this.extension = FilenameUtils.getExtension(fileEntity.getName());
    this.modificationDate = SIMPLE_DATE_FORMAT.format(fileEntity.getModificationDate());
    this.viewable = Arrays.asList(fileEntity.getViewList().split(",")).contains(userId);
    this.editable = Arrays.asList(fileEntity.getEditList().split(",")).contains(userId);
    this.removable = Arrays.asList(fileEntity.getRemoveList().split(",")).contains(userId);
  }


  public static Collection<FileListView> of(Collection<FileInfo> fileInfos,
                                            Long userId) {
    List<FileListView> permittedFiles = new ArrayList<>(fileInfos.size());
    permittedFiles.addAll(fileInfos.stream()
        .map(filePlainInfo -> new FileListView(filePlainInfo, userId))
        .collect(Collectors.toList()));
    return permittedFiles;
  }

  public static String readableFileSize(long size) {
    if (size <= 0) return "0";
    final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
    int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
    return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public String getSize() {
    return size;
  }

  public String getExtension() {
    return extension;
  }

  public String getModificationDate() {
    return modificationDate;
  }

  public boolean isViewable() {
    return viewable;
  }

  public boolean isEditable() {
    return editable;
  }

  public boolean isRemovable() {
    return removable;
  }
}

package me.box.files;

import me.box.files.support.FileInfo;
import me.box.files.support.FileSaver;
import me.box.files.support.model.FileDownloadInfo;
import me.box.users.UserRepository;
import me.box.users.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service("fileService")
public class FileService {

  private FileRepository fileRepository;
  private FileSaver fileSaver;
  private UserRepository userRepository;

  @Autowired
  public FileService(FileRepository fileRepository, FileSaver fileSaver, UserRepository userRepository) {
    this.fileRepository = fileRepository;
    this.fileSaver = fileSaver;
    this.userRepository = userRepository;
  }

  @Transactional
  public boolean saveFile(String username, CommonsMultipartFile multipartFile) {
    UsersEntity author = userRepository.findByUsername(username);
    String originalFileName = multipartFile.getOriginalFilename();

    FilesEntity filesEntity = new FilesEntity();
    filesEntity.setName(originalFileName);
    filesEntity.setAuthor(author);
    filesEntity.setViewList(String.valueOf(author.getId()));
    filesEntity.setEditList(String.valueOf(author.getId()));
    filesEntity.setRemoveList(String.valueOf(author.getId()));

    Long idOfFile = (Long) fileRepository.create(filesEntity);
    if (fileSaver.save(multipartFile, fileRepository.read(idOfFile))) {
      return true;
    } else {
      filesEntity.setId(idOfFile);
      fileRepository.delete(filesEntity);
    }
    return false;
  }

  @Transactional
  public Collection<FileInfo> getFileListForUsername(String username) {
    Collection<FilesEntity> filesEntities = fileRepository.findAll();
    return filesEntities.stream().map(fileSaver::getFilePlainInfo).collect(Collectors.toList());
  }

  @Transactional
  public FileDownloadInfo getDownloadableFileInfo(Long id) throws FileNotFoundException {
    FilesEntity fileEntity = fileRepository.read(id);
    FileInfo fileInfo = fileSaver.getFilePlainInfo(fileEntity);
    return new FileDownloadInfo(fileEntity, fileInfo);
  }

  public void transferFile(OutputStream outputStream, Long id) {
    FilesEntity fileEntity = fileRepository.read(id);
    fileSaver.transfer(fileEntity, outputStream);
  }

  @Transactional
  public boolean userCanView(Long id, String username) {
    FilesEntity fileEntity = fileRepository.read(id);
    UsersEntity usersEntity = userRepository.findByUsername(username);
    String userId = String.valueOf(usersEntity.getId());
    return Arrays.asList(fileEntity.getViewList().split(",")).contains(userId);
  }

  @Transactional
  public boolean userCanEdit(Long id, String username) {
    FilesEntity fileEntity = fileRepository.read(id);
    UsersEntity usersEntity = userRepository.findByUsername(username);
    String userId = String.valueOf(usersEntity.getId());
    return Arrays.asList(fileEntity.getEditList().split(",")).contains(userId);
  }

  @Transactional
  public boolean userCanRemove(Long id, String username) {
    FilesEntity fileEntity = fileRepository.read(id);
    UsersEntity usersEntity = userRepository.findByUsername(username);
    String userId = String.valueOf(usersEntity.getId());
    return Arrays.asList(fileEntity.getRemoveList().split(",")).contains(userId);
  }

  @Transactional
  public FilesEntity readFile(Long id) {
    return fileRepository.read(id);
  }

  @Transactional
  public void updateFile(FilesEntity filesEntity) {
    fileRepository.update(filesEntity);
  }

  @Transactional
  public void removeFile(Long id) {
    FilesEntity fileEntity = fileRepository.read(id);
    fileRepository.deleteById(id);
    fileSaver.remove(fileEntity);
  }
}

package me.box.files;

import me.box.files.support.listeners.FileRequestListener;
import me.box.files.support.model.*;
import me.box.files.support.model.adapter.FileEditFormAdapter;
import me.box.files.support.model.adapter.FileShareEditFormAdapter;
import me.box.support.UploadableController;
import me.box.users.UserService;
import me.box.users.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

@Controller
@RequestMapping(value = "/files")
public class FileController extends UploadableController {

  @Autowired
  private FileService fileService;

  @Autowired
  private UserService userService;

  @Autowired
  private FileEditFormAdapter fileEditFormAdapter;

  @Autowired
  private FileShareEditFormAdapter fileShareEditFormAdapter;

  @Autowired
  private FileRequestListener fileViewRequestDenialListener;

  @Autowired
  private FileRequestListener fileEditRequestDenialListener;

  @Autowired
  private FileRequestListener fileRemoveRequestDenialListener;

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String processSubmit(@ModelAttribute("uploadForm") FileUploadForm form,
                              Authentication authentication) {
    fileService.saveFile(authentication.getName(), form.getMultipartFile());
    return "redirect:/files/list";
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String home(ModelMap model, Authentication authentication) {
    String username = authentication.getName();

    UsersEntity usersEntity = userService.findByUsername(username);
    Collection<FileListView> fileListViews = FileListView.of(fileService.getFileListForUsername(username)
        , usersEntity.getId());

    model.addAttribute("fileList", fileListViews);
    return "files/list";
  }

  @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
  public void download(@PathVariable Long id,
                       HttpServletResponse response,
                       Authentication authentication) {
    if (fileService.userCanView(id, authentication.getName())) {
      OutputStream outputStream = null;
      try {
        FileDownloadInfo fileDownloadInfo = fileService.getDownloadableFileInfo(id);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", fileDownloadInfo.getName());
        response.setHeader(headerKey, headerValue);

        response.setContentType(fileDownloadInfo.getMimeType());
        response.setContentLength(fileDownloadInfo.getLength());

        outputStream = response.getOutputStream();
        fileService.transferFile(outputStream, id);

      } catch (IOException ignored) {
      } finally {
        try {
          if (outputStream != null) {
            outputStream.close();
          }
        } catch (IOException ignored) {
        }
      }
    }
  }

  @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
  public String view(@PathVariable Long id,
                     ModelMap model,
                     Authentication authentication) {
    if (!fileService.userCanView(id, authentication.getName()))
      return handleNoViewPermission(model, id, authentication.getName());

    return "redirect:/files/download/" + id;
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(@PathVariable Long id,
                     ModelMap model,
                     Authentication authentication) {
    if (!fileService.userCanEdit(id, authentication.getName()))
      return handleNoEditPermission(model, id, authentication.getName());

    FilesEntity filesEntity = fileService.readFile(id);
    FileEditForm fileEditForm = new FileEditForm(filesEntity);

    model.addAttribute("fileEditForm", fileEditForm);

    return "files/edit";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
  public String edit(@PathVariable Long id,
                     ModelMap model,
                     Authentication authentication,
                     @ModelAttribute("fileEditForm") @Valid FileEditForm form,
                     BindingResult result) {
    if (!fileService.userCanEdit(id, authentication.getName()))
      return handleNoEditPermission(model, id, authentication.getName());

    if (result.hasErrors()) {
      model.addAttribute("fileEditForm", form);
      return "files/edit";
    }

    FilesEntity filesEntity = fileService.readFile(id);
    fileEditFormAdapter.updateFileEntityFromFileEditForm(filesEntity, form);
    fileService.updateFile(filesEntity);

    return "redirect:/files/list";
  }

  @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
  public String remove(@PathVariable Long id,
                       ModelMap model,
                       Authentication authentication) {
    if (!fileService.userCanRemove(id, authentication.getName()))
      return handleNoRemovePermission(model, id, authentication.getName());

    fileService.removeFile(id);

    return "redirect:/files/list";
  }

  @RequestMapping(value = "/share/{id}", method = RequestMethod.GET)
  public String editSharing(@PathVariable Long id,
                            ModelMap model) {

    FilesEntity filesEntity = fileService.readFile(id);
    FileShareEditForm fileShareEditForm = new FileShareEditForm(filesEntity);
    Collection<UserPlainView> userPlainViews = UserPlainView.of(userService.findAll());

    model.addAttribute("filePermissionEditForm", fileShareEditForm);
    model.addAttribute("userPlainViews", userPlainViews);

    return "files/share";

  }

  @RequestMapping(value = "/share/{id}", method = RequestMethod.POST)
  public String editSharing(@PathVariable Long id,
                            @ModelAttribute("fileShareEditForm") @Valid FileShareEditForm form,
                            BindingResult result,
                            ModelMap model) {
    if (result.hasErrors()) {
      Collection<UserPlainView> userPlainViews = UserPlainView.of(userService.findAll());

      model.addAttribute("filePermissionEditForm", form);
      model.addAttribute("userPlainViews", userPlainViews);

      return "files/share";
    }

    FilesEntity filesEntity = fileService.readFile(id);
    fileShareEditFormAdapter.updateFileEntityFromFileShareEditForm(filesEntity, form);
    fileService.updateFile(filesEntity);

    return "redirect:/files/list";
  }

  @ModelAttribute("fileEditForm")
  public FileEditForm fileEditForm() {
    return new FileEditForm();
  }

  @ModelAttribute("fileShareEditForm")
  public FileShareEditForm fileShareEditForm() {
    return new FileShareEditForm();
  }

  private String handleNoViewPermission(ModelMap model, Long id, String username) {
    fileViewRequestDenialListener.handle(id, username);

    model.addAttribute("message", "You can't view this file.");
    return "files/description";
  }

  private String handleNoEditPermission(ModelMap model, Long id, String username) {
    fileEditRequestDenialListener.handle(id, username);

    model.addAttribute("message", "You can't edit this file.");
    return "files/description";
  }

  private String handleNoRemovePermission(ModelMap model, Long id, String username) {
    fileRemoveRequestDenialListener.handle(id, username);

    model.addAttribute("message", "You can't remove this file.");
    return "files/description";
  }
}
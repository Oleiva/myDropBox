package io.github.oleiva.myDropBox.users;

import io.github.oleiva.myDropBox.support.UploadableController;
import io.github.oleiva.myDropBox.users.support.model.UserAddForm;
import io.github.oleiva.myDropBox.users.support.model.UserEditForm;
import io.github.oleiva.myDropBox.users.support.model.UserListView;
import io.github.oleiva.myDropBox.users.support.model.adapter.UserAddFormAdapter;
import io.github.oleiva.myDropBox.users.support.model.adapter.UserEditFormAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping(value = "/users")
public class UsersController extends UploadableController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserAddFormAdapter userAddFormAdapter;

  @Autowired
  private UserEditFormAdapter userEditFormAdapter;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String load(ModelMap model) {
    Collection<UserListView> userListViews = UserListView.of(userService.findAll());

    model.addAttribute("userList", userListViews);
    return "users/list";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add() {
    return "users/add";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String add(@ModelAttribute("userAddForm") @Valid UserAddForm form,
                    BindingResult result) {
    if (!form.getPassword().equals(form.getConfirmPassword())) {
      result.rejectValue("confirmPassword", "error.confirmPassword", "password doesn't match");
    }
    if (result.hasErrors()) {
      return "users/add";
    }
    // act normal
    UsersEntity user = new UsersEntity();
    userAddFormAdapter.updateUserEntityFromUserAddForm(user, form);
    userService.create(user);
    return "redirect:/users/list";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(@PathVariable Long id,
                     ModelMap model) {

    UsersEntity user = userService.read(id);
    UserEditForm userEditForm = new UserEditForm(user);

    model.addAttribute("userEditForm", userEditForm);
    return "users/edit";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
  public String edit(@PathVariable Long id,
                     @ModelAttribute("userEditForm") @Valid UserEditForm form,
                     BindingResult result,
                     ModelMap model) {
    UsersEntity user = userService.read(id);
    if (!result.hasErrors()) {
      if (BCrypt.checkpw(form.getOldPassword(), user.getPassword())) {
        // we're good
        if (!user.isAccountNonLocked() && form.getAccountNonLocked() != 0) userService.unlock(user.getUsername());
        userEditFormAdapter.updateUserEntityFromUserEditForm(user, form);
        userService.update(user);
        return "redirect:/users/list";
      } else {
        result.rejectValue("oldPassword", "error.oldPassword", "incorrect password");
      }
    }
    form.setLastUpdate(user.getLastUpdate());
    model.addAttribute("userEditForm", form);
    return "users/edit";
  }


  @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
  public String remove(@PathVariable Long id) {
    userService.delete(id);
    return "redirect:/users/list";
  }

  @ModelAttribute("userAddForm")
  public UserAddForm userAddForm() {
    return new UserAddForm();
  }

  @ModelAttribute("userEditForm")
  public UserEditForm userEditForm() {
    return new UserEditForm();
  }
}

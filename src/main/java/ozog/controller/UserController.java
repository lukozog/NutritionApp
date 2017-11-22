package ozog.controller;


import org.springframework.web.bind.WebDataBinder;
import ozog.model.User;
import ozog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ozog.validator.UserValidator;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserValidator());
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }


            @Override
            public String getAsText() throws IllegalArgumentException {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
            }
        });
    }

    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "allusers";

    }

    @RequestMapping(value = {"/users/newUser"}, method = RequestMethod.GET)
    public String newUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("add", true);
        modelMap.addAttribute("user", user);
        return "adduser";
    }

    @RequestMapping(value = {"/users/newUser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult,
                           ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("add", true);
            return "adduser";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = {"/users/updateUser/{userId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable long userId, ModelMap modelMap) {
        User user = userService.getUserById(userId);
        modelMap.addAttribute("user", user);
        return "adduser";
    }

    @RequestMapping(value = {"/users/updateUser/{userId}"}, method = RequestMethod.POST)
    public String editUser(@PathVariable long userId, @Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = {"/users/deleteUser/{userId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable long userId, ModelMap modelMap) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }


}

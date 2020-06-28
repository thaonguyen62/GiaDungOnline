package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.dto.UserDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.service.UserService;
import ltwwwjava.btl.dogiadungtructuyen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String signIn(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("personForm") UserDTO personForm) throws ResourceNotFoundException {
        return "redirect:/products";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        UserDTO personForm = new UserDTO();
        model.addAttribute("personForm", personForm);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("personForm") UserDTO personForm) throws ResourceNotFoundException {

        User user = new User();
        user.setAccountType(Constants.CUSTOMER_TYPE);
        user.setAddress(personForm.getAddress());
        user.setMail(personForm.getMail());
        user.setPhone(personForm.getPhone());
        user.setPassword(personForm.getPassword());
        user.setName(personForm.getName());
        user.setUsername(personForm.getUsername());
        userService.createAndUpdate(user);
        return "redirect:/login";
    }

}

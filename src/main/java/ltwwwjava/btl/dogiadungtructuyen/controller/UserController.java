package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.dto.UserDTO;
import ltwwwjava.btl.dogiadungtructuyen.dto.ValidateDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.UserService;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.ProductImpl;
import ltwwwjava.btl.dogiadungtructuyen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private ProductImpl productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    //localhost:8087/houseware-service/products

    @GetMapping("/login")
    public String signIn(Model model) {
        UserDTO personForm = new UserDTO();
        model.addAttribute("personFormLogin", personForm);
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("personFormLogin") UserDTO personForm) throws ResourceNotFoundException {

        if (userService.checkUserNameAndPassword(personForm.getUsername(), personForm.getPassword()))
            return "redirect:/products";
        model.addAttribute("errorMessageLogin", Constants.USERNAME_PASSWORD_IS_INCORRECT);
        return "login";
    }
    //nhanvien 0
    @GetMapping("/employeeManager")
    public String getlistEmployee(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        List<User> userList = userService.findByAccountType(0);
        model.addAttribute("userList",userList);
        return "listUser";
    }
    //khach hang 1
    @GetMapping("/customerManager")
    public String getlistCustomer(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        List<User> userList = userService.findByAccountType(1);
        model.addAttribute("userList",userList);
        return "listUser";
    }
    @GetMapping("/editUser/{id}")
    public String geteditUser(@PathVariable(value = "id") String id, Model model) throws ResourceNotFoundException {
       User personForm = userService.findById(id);

        model.addAttribute("personForm", personForm);
        return "editUser";
    }
    @GetMapping("/deleteUser")
    public String getdeleteUser(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        UserDTO personForm = new UserDTO();
        model.addAttribute("personForm", personForm);
        return "listUser";
    }
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        UserDTO personForm = new UserDTO();
        model.addAttribute("personForm", personForm);

        return "register";
    }


    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("personForm") UserDTO personForm) throws ResourceNotFoundException {

        if (!checkInvalid(personForm).isInvalid()) {
            model.addAttribute("errorMessage", checkInvalid(personForm).getMessage());
        } else {
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
        return "register";
    }

    private ValidateDTO checkInvalid(UserDTO personForm) {
        ValidateDTO validateDTO = new ValidateDTO();
        validateDTO.setInvalid(true);
        if (userService.checkUserNameIsExist(personForm.getUsername())) {
            validateDTO.setMessage(Constants.USERNAME_EXIST);
            validateDTO.setInvalid(false);
        }
        if (!personForm.getPassword().equals(personForm.getRetypePassword())) {
            validateDTO.setMessage(Constants.INVALID_PASSWORD);
            validateDTO.setInvalid(false);
        }
        return validateDTO;
    }



}


package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.ProductDTO;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @GetMapping("/login")
    public String signIn(Model model) {
        return "signin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "signup";
    }

}
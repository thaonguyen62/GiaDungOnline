package ltwwwjava.btl.dogiadungtructuyen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

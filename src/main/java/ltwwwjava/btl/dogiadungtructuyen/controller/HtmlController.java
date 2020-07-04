package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.dto.UserDTO;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.UserService;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class HtmlController {
    @Autowired
    private ProductImpl productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    UserService userService;
    //localhost:8087/houseware-service/products
  
//    @GetMapping("/editUser")
//    public String geteditUser(Model model) {
//        List<Category> listCat = categoryRepository.findAll();
//        UserDTO personForm = new UserDTO();
//        model.addAttribute("personForm", personForm);
//        return "editUser";
//    }
    @GetMapping("/about")
    public String get(Model model, HttpSession  session) {
        List<Category> listCat = categoryRepository.findAll();
        boolean hidden = false;
        if (!session.getAttributeNames().hasMoreElements()) {
            hidden = false;
        } else {
            hidden = userService.checkAdmin(session.getAttribute("mySessionAttribute").toString());
        }
        model.addAttribute("hiddenManagement", hidden);
        model.addAttribute("categories",listCat);
        return "about";
    }
    @GetMapping("/about2")
    public String getabout2(Model model,HttpSession session) {
        List<Category> listCat = categoryRepository.findAll();
        boolean hidden = false;
        if (!session.getAttributeNames().hasMoreElements()) {
            hidden = false;
        } else {
            hidden = userService.checkAdmin(session.getAttribute("mySessionAttribute").toString());
        }
        model.addAttribute("hiddenManagement", hidden);
        model.addAttribute("categories",listCat);
        return "about2";
    }
    @GetMapping("/checkout")
    public String getcheckout(Model model,HttpSession session) {
        List<Category> listCat = categoryRepository.findAll();
        boolean hidden = false;
        if (!session.getAttributeNames().hasMoreElements()) {
            hidden = false;
        } else {
            hidden = userService.checkAdmin(session.getAttribute("mySessionAttribute").toString());
        }
        model.addAttribute("hiddenManagement", hidden);
        model.addAttribute("categories",listCat);
        return "checkout";
    }
    @GetMapping("/contact")
    public String getcontact(Model model,HttpSession session) {
        List<Category> listCat = categoryRepository.findAll();
        boolean hidden = false;
        if (!session.getAttributeNames().hasMoreElements()) {
            hidden = false;
        } else {
            hidden = userService.checkAdmin(session.getAttribute("mySessionAttribute").toString());
        }
        model.addAttribute("hiddenManagement", hidden);
        model.addAttribute("categories",listCat);
        return "contact";
    }
    @GetMapping("/document")
    public String getdocument(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "document";
    }
    @GetMapping("/insertProduct")
    public String getinsertProduct(Model model,HttpSession session) {
        List<Category> listCat = categoryRepository.findAll();
        boolean hidden = false;
        if (!session.getAttributeNames().hasMoreElements()) {
            hidden = false;
        } else {
            hidden = userService.checkAdmin(session.getAttribute("mySessionAttribute").toString());
        }
        model.addAttribute("hiddenManagement", hidden);
        model.addAttribute("categories",listCat);
        return "insertProduct";
    }
    @GetMapping("/recruitment")
    public String getecruitment(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "recruitment";
    }
    @GetMapping("/warrantyPolicy")
    public String getwarrantyPolicy(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "warrantyPolicy";
    }
    @GetMapping("/warrantySystem")
    public String getwarrantySystem(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "warrantySystem";
    }

    @GetMapping("/edit-cat")
    public String getSystem(Model model) {

        return "edit-cat";
    }

    



}

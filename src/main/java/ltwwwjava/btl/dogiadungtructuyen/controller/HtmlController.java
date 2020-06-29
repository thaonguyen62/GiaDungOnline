package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class HtmlController {
    @Autowired
    private ProductImpl productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    //localhost:8087/houseware-service/products
    @GetMapping("/about")
    public String getabout(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "about";
    }
    @GetMapping("/about2")
    public String getabout2(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "about2";
    }
    @GetMapping("/checkout")
    public String getcheckout(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "checkout";
    }
    @GetMapping("/contact")
    public String getcontact(Model model) {
        List<Category> listCat = categoryRepository.findAll();
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
    public String getinsertProduct(Model model) {
        List<Category> listCat = categoryRepository.findAll();
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


}

package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.CategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryImpl categoryService;

    @RequestMapping(value = {"/add-category"}, method = RequestMethod.GET)
    public String showAddProductPage(Model model) {
        Category  category= new Category();

        model.addAttribute("category", category);
        return "category";
    }

    @PostMapping("/add-category")
    public String addCategory(Model model, @ModelAttribute("category") Category category) throws ResourceNotFoundException {

        categoryService.createAndUpdateCategory(category);
        return "redirect:/products";

    }

    @GetMapping("/edit-category/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) throws ResourceNotFoundException {

        Category product = categoryService.findCategoryById(id);
        model.addAttribute("category",product);
        List<Category> list = categoryService.getAllCategory();
        model.addAttribute("list", list);
        return "edit-cat";
    }

    @PostMapping("/edit-category/{id}")
    public String updateProduct(@PathVariable(value = "id") String id, Model model, @Valid Category category, BindingResult result) throws ResourceNotFoundException {
        if (result.hasErrors()) {
            category.setId(id);
            return "edit-cat";
        }
        categoryService.createAndUpdateCategory(category);
        model.addAttribute("category", categoryService.getAllCategory());
        return "edit-cat";

    }

}

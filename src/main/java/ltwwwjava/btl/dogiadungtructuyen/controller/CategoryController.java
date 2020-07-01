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
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryImpl categoryService;

    @GetMapping("/list-cats")
    public String getlistCat(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories",listCat);
        return "list-cat";
    }

    @RequestMapping(value = {"/add-category"}, method = RequestMethod.GET)
    public String showAddProductPage(Model model) {
        Category  category= new Category();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        return "add-cat";
    }

    @PostMapping("/add-category")
    public String addCategory(Model model, @ModelAttribute("category") Category category) throws ResourceNotFoundException {

        categoryService.createAndUpdateCategory(category);
        return "redirect:/list-cats";

    }

    @GetMapping("/edit-category/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) throws ResourceNotFoundException {

        Category product = categoryService.findCategoryById(id);
        model.addAttribute("category",product);
        List<Category> list = categoryService.getAllCategory();
        model.addAttribute("list", list);
        return "edit-cat";
    }

    @PostMapping("/edit-categories/{id}")
    public String updateProduct(@PathVariable(value = "id") String id, Model model, @Valid Category category, BindingResult result) throws ResourceNotFoundException {
        if (result.hasErrors()) {
            category.setId(id);
            return "edit-cat";
        }
        categoryService.createAndUpdateCategory(category);
        model.addAttribute("category", categoryService.getAllCategory());
        return "redirect:/list-cats";

    }

    @GetMapping("delete-cat/{id}")
    public String deleteCat(@PathVariable("id") String id, Model model) throws  ResourceNotFoundException{
        categoryService.deleteCategoryById(id);
        List<Category> list = categoryService.getAllCategory();
        model.addAttribute("list", list);
        return "redirect:/list-cats";
    }

}

package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.ProductDTO;
import ltwwwjava.btl.dogiadungtructuyen.dto.UserDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.ProductImpl;
import ltwwwjava.btl.dogiadungtructuyen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductImpl productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
//localhost:8087/houseware-service/products
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> list = productService.findAll();
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("listProduct", list);
        model.addAttribute("categories",listCat);
        return "index";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable(value = "id") String id, Model model) throws ResourceNotFoundException {
        Product product = productService.findById(id);

        model.addAttribute("product", product);
        return "single";
    }

    @RequestMapping(value = {"/add-product"}, method = RequestMethod.GET)
    public String showAddProductPage(Model model) {
        Product product = new Product();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("product", product);
        return "insertProduct";
    }

    @PostMapping("/add-product")
    public String addProduct(Model model, @ModelAttribute("Product") Product product) throws ResourceNotFoundException {


        /*Product product = new Product();
        //product.setAccountType(Constants.CUSTOMER_TYPE);
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setFileName(productDTO.getFileName());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());*/
        productService.createAndUpdate(product);
        return "redirect:/products";

}

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) throws ResourceNotFoundException {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "update-product";
    }

    @PutMapping("/edit-products/id}")
    public String updateProduct(@PathVariable(value = "id") String id, Model model, @Valid Product product, BindingResult result) throws ResourceNotFoundException {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }

        productService.createAndUpdate(product);
        model.addAttribute("products", productService.findAll());
        return "redirect:/index";

    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id, Model model) throws ResourceNotFoundException {
        productService.delete(id);
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/products/product")
    public String getProductByName(@Param(value = "search") String search, Model model) throws ResourceNotFoundException {
        List<Product> list = productService.findProductByName(search);
        model.addAttribute("products", list);
        return "product";
    }


}

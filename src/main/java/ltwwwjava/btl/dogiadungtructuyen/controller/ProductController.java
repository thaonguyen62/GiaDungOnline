package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductImpl productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private final String UPLOAD_DIR = "src\\main\\resources\\static\\images\\";

    @GetMapping("/products")
    public String getAllProducts(Model model) throws ResourceNotFoundException{
        List<Product> list = productService.findAll();
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("listProduct", list);
        model.addAttribute("categories", listCat);
        List<Product> list1=productService.findProductByCategory(listCat.get(0).getId());
        model.addAttribute("listProductDoGiaDungNhat",list1);
        List<Product> list2=productService.findProductByCategory(listCat.get(1).getId());
        model.addAttribute("listProductBepDien",list2);
        return "index";
    }


    @GetMapping("/cart")
     public String getAllProductsd(Model model) {

        return "cartt";
    }
    @GetMapping("/dshd")
    public String getAllProductsdd(Model model) {

        return "dshdd";
    }


    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable(value = "id") String id, Model model) throws ResourceNotFoundException {
        Optional<Category> c = categoryRepository.findById(id);

        model.addAttribute("c", c);
        return "single";
    }

    @RequestMapping(value = {"/add-product"}, method = RequestMethod.GET)
    public String showAddProductPage(Model model) {
        Product product = new Product();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "insertProduct";
    }

    @PostMapping("/add-product")
    public String addProduct(Model model, @ModelAttribute("Product") Product product, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws ResourceNotFoundException {
        List<String> images = new ArrayList<>();
        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            images.add(path.toString().replace("src\\main\\resources\\static\\",""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');


        product.setFileName(images);
        productService.createAndUpdate(product);
        return "redirect:/products";

    }

    @GetMapping("/edit-products")
    public String showUpdateForm(@PathVariable("id") String id, Model model) throws ResourceNotFoundException {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PutMapping("/edit-products")
    public String updateProduct(@PathVariable(value = "id") String id, Model model, @Valid Product product, BindingResult result) throws ResourceNotFoundException {
        if (result.hasErrors()) {
            product.setId(id);
            return "edit-product";
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
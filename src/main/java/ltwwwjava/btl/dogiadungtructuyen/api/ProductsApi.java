package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.ProductDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.CategoryService;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@RestController
*/
public class ProductsApi {
    @Autowired
    private ProductImpl productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/products")
    @Dto(value = ProductDTO.class)
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    @Dto(value = ProductDTO.class)
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) throws ResourceNotFoundException {
        Product p = productService.createAndUpdate(product);
        return p;
    }

    @PutMapping("/products/{id}")
    @Dto(value = ProductDTO.class)
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Product p = productService.createAndUpdate(product);
        return ResponseEntity.ok(p);

    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        boolean p = productService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", p);
        return response;
    }

    @GetMapping("/products/product")
    @Dto(value = ProductDTO.class)
    public List<Product> getProductByName(@RequestParam(value = "name") String name) throws ResourceNotFoundException {
        return productService.findProductByName(name);
    }

    /*
        Search list Product By Category
    * */
    @GetMapping("/products/category")
    @Dto(value = ProductDTO.class)
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam(value = "cat") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productService.findProductByCategory(id));
    }


}

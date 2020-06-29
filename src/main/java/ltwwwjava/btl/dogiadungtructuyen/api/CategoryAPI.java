package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.CategoryDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.CategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
public class
CategoryAPI {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryImpl categoryService;

    @GetMapping("/categories")
    @Dto(value = CategoryDTO.class)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    @Dto(value = CategoryDTO.class)
    public ResponseEntity<?> getCategoryById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @GetMapping("/categories/category")
    @Dto(value = CategoryDTO.class)
    public ResponseEntity<?> getCategoryByName(@RequestParam(value = "name") String name) throws ResourceNotFoundException {
        List<Category> category = categoryService.getCategoryByName(name);
        return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) throws ResourceNotFoundException {
        Category category1 = categoryService.createAndUpdateCategory(category);
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @Valid @RequestBody Category category) throws ResourceNotFoundException {
        if(!categoryRepository.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        Category cat = categoryService.createAndUpdateCategory(category);
        return ResponseEntity.ok(cat);

    }

    @DeleteMapping("/categories/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        boolean cat = categoryService.deleteCategoryById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", cat);
        return response;
    }
}

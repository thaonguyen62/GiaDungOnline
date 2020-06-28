package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.CategoryDTO;
import ltwwwjava.btl.dogiadungtructuyen.dto.ProductDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ExistedException;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Dto(CategoryDTO.class)
    public Category findCategoryById(String id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found this id " + id));
        return category;
    }

    @Dto(CategoryDTO.class)
    public List<Category> getCategoryByName(String name) throws ResourceNotFoundException {
        List<Category> list = categoryRepository.findCategoriesByNameContaining(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found this name " + name));
        System.out.println(list.size());
        return list;
    }


    public Category createAndUpdateCategory(Category category) throws ResourceNotFoundException {
        Optional<Category> cat = categoryRepository.findByName(category.getName());
        if (cat.isPresent()) {
            throw new ResourceNotFoundException(category.getName() + " existed");
        }
        return categoryRepository.save(category);
    }

    public boolean deleteCategoryById(String id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found this id " + id));
        categoryRepository.delete(category);
        return true;
    }


}

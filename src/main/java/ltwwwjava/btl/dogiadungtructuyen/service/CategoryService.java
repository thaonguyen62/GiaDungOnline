package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category findCategoryById(String id) throws ResourceNotFoundException;

    List<Category> getCategoryByName(String name) throws ResourceNotFoundException;

    Category createAndUpdateCategory(Category category) throws ResourceNotFoundException;

    boolean deleteCategoryById(String id) throws ResourceNotFoundException;
}

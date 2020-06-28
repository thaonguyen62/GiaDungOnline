package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.ProductDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Dto(ProductDTO.class)
    public Product findById(String id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        return product;
    }

    public Product createAndUpdate(Product product) throws ResourceNotFoundException {
        Category cat = categoryRepository.findById(product.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id: "
                        + product.getCategory()));
        List<Product> list = productRepository.findByNameContaining(product.getName());
        return productRepository.save(product);
    }

    public boolean delete(String id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
        productRepository.delete(product);
        return true;
    }

    @Dto(ProductDTO.class)
    public List<Product> findProductByName(String name) throws ResourceNotFoundException {
        List<Product> product = productRepository.findByNameContaining(name);
        if(product.isEmpty()){
            throw new ResourceNotFoundException("Product not existed for this name: "
                    + name);
        }
        return product;
    }
    @Dto(ProductDTO.class)
    public List<Product> findProductByCategory(String idCat) throws ResourceNotFoundException {
        List<Product> products = productRepository.findByCategory(idCat);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product not found for this category: " + idCat);
        }
        return products;
    }
}

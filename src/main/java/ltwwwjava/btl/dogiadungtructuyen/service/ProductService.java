package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(String id) throws ResourceNotFoundException;

    Product createAndUpdate(Product product) throws ResourceNotFoundException;

    boolean delete(String id) throws ResourceNotFoundException;

    List<Product> findProductByName(String name) throws ResourceNotFoundException;

    List<Product> findProductByCategory(String idCat) throws ResourceNotFoundException;

}

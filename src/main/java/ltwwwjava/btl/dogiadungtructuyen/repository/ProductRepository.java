package ltwwwjava.btl.dogiadungtructuyen.repository;


import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNameContaining(String name);

    List<Product> findByCategory(String id);

    Product findByName(String name);

}

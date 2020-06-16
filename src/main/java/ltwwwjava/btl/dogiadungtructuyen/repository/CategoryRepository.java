package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends MongoRepository<Category,String> {
    Optional<List<Category>> findCategoriesByNameContaining(String name);
    Optional<Category> findByName(String name);

}

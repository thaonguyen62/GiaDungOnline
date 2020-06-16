package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.model.Order;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByCustomer(String id);

    Optional<Order> findByProducts(String id);

}

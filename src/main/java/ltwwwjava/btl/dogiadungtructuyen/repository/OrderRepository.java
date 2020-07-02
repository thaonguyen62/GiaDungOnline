package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<OrderDetail, String> {

    List<OrderDetail> findByCustomer(String id);

    Optional<OrderDetail> findByProducts(String id);
    Optional<OrderDetail> findByCustomerAndProductsId(String customer, String productId);

}

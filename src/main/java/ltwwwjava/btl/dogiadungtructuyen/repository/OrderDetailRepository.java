package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends MongoRepository<OrderDetail, String> {

    List<OrderDetail> findByCustomerAndStatus(String id,int status);

    Optional<OrderDetail> findByProducts(String id);
    Optional<OrderDetail> findByCustomerAndProductsIdAndStatus(String customer, String productId,int status);

}

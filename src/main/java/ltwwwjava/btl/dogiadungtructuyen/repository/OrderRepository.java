package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}

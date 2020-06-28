package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<User, String> {
    Optional<List<User>> findCustomerByAddress(String add);
    User findCustomerByPhoneAndMail(String phone, String mail);
    User findByUsername(String userName);
}

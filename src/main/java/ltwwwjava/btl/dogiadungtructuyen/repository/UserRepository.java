package ltwwwjava.btl.dogiadungtructuyen.repository;

import ltwwwjava.btl.dogiadungtructuyen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<List<User>> findBy(String add);

    Optional<User> findCustomerByPhoneAndMail(String phone, String mail);

    Optional<User> findByUsername(String userName);

    Optional<User> findByUsernameAndPassword(String username, String password);

    List<User> find
}

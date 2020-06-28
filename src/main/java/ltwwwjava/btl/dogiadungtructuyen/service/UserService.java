package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllCustomer();

    User findById(String id) throws ResourceNotFoundException;

    List<User> findByAddress(String add) throws ResourceNotFoundException;

    User createAndUpdate(User user) throws ResourceNotFoundException;

    boolean deleteCustomerById(String id) throws ResourceNotFoundException;
}

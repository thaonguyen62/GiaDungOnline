package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllCustomer();

    User findByUserName(String userName) throws ResourceNotFoundException;

    User findById(String id) throws ResourceNotFoundException;

    List<User> findByAccountType(int loai);

    List<User> findByAddress(String add) throws ResourceNotFoundException;

    User createAndUpdate(User user) throws ResourceNotFoundException;

    boolean deleteCustomerById(String id) throws ResourceNotFoundException;

    boolean checkUserNameIsExist(String userName);

    boolean checkUserNameAndPassword(String username,String password);

    boolean checkAdmin(String username);
}

package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.repository.CustomerRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private CustomerRepository customerRepository;
    /*@Autowired
    PasswordEncoder passwordEncoder;*/

    public List<User> getAllCustomer(){
        return customerRepository.findAll();
    }

    public User findById(String id) throws ResourceNotFoundException{
        User user = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this id " + id));
        return user;
    }

    public List<User> findByAddress(String add) throws ResourceNotFoundException{
        List<User> list = customerRepository.findCustomerByAddress(add)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this address " + add));
        return list;
    }

    public User createAndUpdate(User user) throws ResourceNotFoundException{
        User cu = user;
        User cus = customerRepository.findCustomerByPhoneAndMail(user.getPhone(), user.getMail());
        if(cus != null){
            throw new ResourceNotFoundException("Phone and mail existed");
        }
        //cu.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(cu);
    }

    public boolean deleteCustomerById(String id) throws ResourceNotFoundException{
        User user = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this id " + id));
        customerRepository.delete(user);
        return true;
    }


}

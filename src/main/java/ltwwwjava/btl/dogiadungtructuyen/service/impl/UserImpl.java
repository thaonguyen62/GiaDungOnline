package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.repository.UserRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.EmailService;
import ltwwwjava.btl.dogiadungtructuyen.service.UserService;
import ltwwwjava.btl.dogiadungtructuyen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository customerRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public User findByUserName(String userName) throws ResourceNotFoundException {
        Optional<User> user = customerRepository.findByUsername(userName);
        if (user.isPresent())
            return user.get();
        return null;
    }

    public List<User> getAllCustomer() {
        return customerRepository.findAll();
    }

    public User findById(String id) throws ResourceNotFoundException {
        User user = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this id " + id));
        return user;
    }

    public List<User> findByAccountType(int loai) {
        List<User> list = customerRepository.findByAccountType(loai);
        return list;
    }

    public List<User> findByAddress(String add) throws ResourceNotFoundException {
        List<User> list = customerRepository.findByAddress(add)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this address " + add));
        return list;
    }

    public User createAndUpdate(User user) throws ResourceNotFoundException {
        User cu = user;
//        Optional<User> cus = customerRepository.findCustomerByPhoneAndMail(user.getPhone(), user.getMail());
//        if (cus.isPresent())
//            throw new ResourceNotFoundException("Phone and mail existed");
        //cu.setPassword(passwordEncoder.encode(customer.getPassword()));
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            emailService.sendEmail(user.getMail(), Constants.REGISTRATION);
        });
        return customerRepository.save(cu);
    }

    public boolean deleteCustomerById(String id) throws ResourceNotFoundException {
        User user = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this id " + id));
        customerRepository.delete(user);
        return true;
    }

    @Override
    public boolean checkUserNameIsExist(String userName) {
        Optional<User> user = customerRepository.findByUsername(userName);
        if (user.isPresent())
            return true;
        return false;
    }

    @Override
    public boolean checkUserNameAndPassword(String username, String password) {
        Optional<User> user = customerRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent())
            return true;
        return false;
    }

    @Override
    public boolean checkAdmin(String username) {
        Optional<User> user = customerRepository.findByUsername(username);
        if (!user.isPresent())
            return false;
        if (user.get().getAccountType() == Constants.ADMIN_TYPE)
            return true;
        return false;
    }


}

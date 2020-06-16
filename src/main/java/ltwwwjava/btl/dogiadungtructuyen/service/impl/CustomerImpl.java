package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.repository.CustomerRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    /*@Autowired
    PasswordEncoder passwordEncoder;*/

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer findById(String id) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this id " + id));
        return customer;
    }

    public List<Customer> findByAddress(String add) throws ResourceNotFoundException{
        List<Customer> list = customerRepository.findCustomerByAddress(add)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this address " + add));
        return list;
    }

    public Customer createAndUpdate(Customer customer) throws ResourceNotFoundException{
        Customer cu = customer;
        Customer cus = customerRepository.findCustomerByPhoneAndMail(customer.getPhone(), customer.getMail());
        if(cus != null){
            throw new ResourceNotFoundException("Phone and mail existed");
        }
        //cu.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(cu);
    }

    public boolean deleteCustomerById(String id) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found this id " + id));
        customerRepository.delete(customer);
        return true;
    }


}

package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();

    Customer findById(String id) throws ResourceNotFoundException;

    List<Customer> findByAddress(String add) throws ResourceNotFoundException;

    Customer createAndUpdate(Customer customer) throws ResourceNotFoundException;

    boolean deleteCustomerById(String id) throws ResourceNotFoundException;
}

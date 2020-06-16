package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order getOrderById(String id) throws ResourceNotFoundException;

    Order createAndUpdate(Order order) throws ResourceNotFoundException;

    boolean delete(String id) throws ResourceNotFoundException;

    List<Customer> getOrdersByFilter(String name) throws ResourceNotFoundException;

    List<Order> getAllOrderByCustomer(String id) throws ResourceNotFoundException;

}

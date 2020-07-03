package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(String id) throws ResourceNotFoundException;

    Order createAndUpdate(Order order) throws ResourceNotFoundException;

    boolean delete(String id) throws ResourceNotFoundException;

    List<Order> getAllOrderByCustomer(String id) throws ResourceNotFoundException;

}

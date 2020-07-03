package ltwwwjava.btl.dogiadungtructuyen.service;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAll();

    OrderDetail getOrderById(String id) throws ResourceNotFoundException;

    OrderDetail createAndUpdate(OrderDetail orderDetail) throws ResourceNotFoundException;

    boolean delete(String id) throws ResourceNotFoundException;

    List<User> getOrdersByFilter(String name) throws ResourceNotFoundException;

    List<OrderDetail> getAllOrderByCustomer(String id) throws ResourceNotFoundException;

    void updateQuantity(String id, int quantity);

}

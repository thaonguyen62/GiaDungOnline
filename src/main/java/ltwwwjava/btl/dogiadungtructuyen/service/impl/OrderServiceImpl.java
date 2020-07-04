package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Order;
import ltwwwjava.btl.dogiadungtructuyen.repository.OrderRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order getOrderById(String id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Order createAndUpdate(Order order) throws ResourceNotFoundException {
        return orderRepository.save(order);
    }

    @Override
    public boolean delete(String id) throws ResourceNotFoundException {
        return false;
    }

    @Override
    public List<Order> getAllOrderByCustomer(String id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}

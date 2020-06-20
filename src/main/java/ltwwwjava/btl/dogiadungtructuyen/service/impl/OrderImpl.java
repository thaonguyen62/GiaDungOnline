package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CustomerRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.OrderRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetail> getAll() {
        return orderRepository.findAll();
    }

    public OrderDetail getOrderById(String id) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));
        return orderDetail;
    }

    public OrderDetail createAndUpdate(OrderDetail orderDetail) throws ResourceNotFoundException {
        Customer cus = customerRepository.findById(orderDetail.getCustomer())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found "));
        Product product = productRepository.findById(orderDetail.getProducts())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found "));
        return orderRepository.save(orderDetail);
    }

    public boolean delete(String id) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));
        orderRepository.delete(orderDetail);
        return true;
    }

    public List<Customer> getOrdersByFilter(String name) throws ResourceNotFoundException {
        List<Customer> list = new ArrayList<>();
        List<Product> products = productRepository.findByNameContaining(name);
        if (products.isEmpty()) {
            new ResourceNotFoundException("Order not found for this product: " + name);
        }
        for (Product p : products) {
            OrderDetail o = orderRepository.findByProducts(p.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this product: " + name));
            Customer cus = customerRepository.findById(o.getCustomer())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this product: " + name));
            list.add(cus);
        }
        return list;
    }

    public List<OrderDetail> getAllOrderByCustomer(String id) throws ResourceNotFoundException {
        List<OrderDetail> list = orderRepository.findByCustomer(id);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Order not found ");
        }
        return list;
    }
}

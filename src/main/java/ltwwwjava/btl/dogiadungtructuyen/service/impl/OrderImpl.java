package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.model.Order;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CustomerRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.OrderRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));
        return order;
    }

    public Order createAndUpdate(Order order) throws ResourceNotFoundException {
        Customer cus = customerRepository.findById(order.getCustomer())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found "));
        Product product = productRepository.findById(order.getProducts())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found "));
        return orderRepository.save(order);
    }

    public boolean delete(String id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));
        orderRepository.delete(order);
        return true;
    }

    public List<Customer> getOrdersByFilter(String name) throws ResourceNotFoundException {
        List<Customer> list = new ArrayList<>();
        List<Product> products = productRepository.findByNameContaining(name);
        if (products.isEmpty()) {
            new ResourceNotFoundException("Order not found for this product: " + name);
        }
        for (Product p : products) {
            Order o = orderRepository.findByProducts(p.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this product: " + name));
            Customer cus = customerRepository.findById(o.getCustomer())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this product: " + name));
            list.add(cus);
        }
        return list;
    }

    public List<Order> getAllOrderByCustomer(String id) throws ResourceNotFoundException {
        List<Order> list = orderRepository.findByCustomer(id);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Order not found ");
        }
        return list;
    }
}

package ltwwwjava.btl.dogiadungtructuyen.service.impl;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.OrderDetailDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.repository.OrderDetailRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.ProductRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.UserRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Dto(OrderDetailDTO.class)
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Dto(OrderDetailDTO.class)
    public OrderDetail getOrderById(String id) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));
        return orderDetail;
    }

    public OrderDetail createAndUpdate(OrderDetail orderDetail) throws ResourceNotFoundException {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findByCustomerAndProductsIdAndStatus(orderDetail.getCustomer(), orderDetail.getProducts().getId(),0);
        if (orderDetailOptional.isPresent()) {
            orderDetailOptional.get().setQuantity(orderDetailOptional.get().getQuantity() + orderDetail.getQuantity());
            orderDetailOptional.get().setSubtotal(orderDetailOptional.get().getQuantity()*orderDetailOptional.get().getProducts().getPrice());
            orderDetailOptional.get().setStatus(orderDetail.getStatus());
            return orderDetailRepository.save(orderDetailOptional.get());
        }
        orderDetail.setSubtotal(orderDetail.getQuantity()*orderDetail.getProducts().getPrice());
        return orderDetailRepository.save(orderDetail);
    }

    public boolean delete(String id) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderDetailRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found for this id: " + id));
        orderDetailRepository.delete(orderDetail);
        return true;
    }

    @Dto(OrderDetailDTO.class)
    public List<User> getOrdersByFilter(String name) throws ResourceNotFoundException {
        List<User> list = new ArrayList<>();
        List<Product> products = productRepository.findByNameContaining(name);
        if (products.isEmpty()) {
            new ResourceNotFoundException("Order not found for this product: " + name);
        }
        for (Product p : products) {
            OrderDetail o = orderDetailRepository.findByProducts(p.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this product: " + name));
            User cus = customerRepository.findById(o.getCustomer())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found for this product: " + name));
            list.add(cus);
        }
        return list;
    }

    @Dto(OrderDetailDTO.class)
    public List<OrderDetail> getAllOrderByCustomer(String id) throws ResourceNotFoundException {
        List<OrderDetail> list = orderDetailRepository.findByCustomerAndStatus(id,0);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void updateQuantity(String id, int quantity) {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(id);
        if (quantity == 0)
            orderDetailRepository.deleteById(id);
        else {
            orderDetailOptional.get().setQuantity(quantity);
            orderDetailOptional.get().setSubtotal(orderDetailOptional.get().getQuantity()*orderDetailOptional.get().getProducts().getPrice());
            orderDetailRepository.save(orderDetailOptional.get());
        }
    }
}

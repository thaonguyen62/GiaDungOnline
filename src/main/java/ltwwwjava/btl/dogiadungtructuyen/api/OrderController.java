package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.CustomerDTO;
import ltwwwjava.btl.dogiadungtructuyen.dto.OrderDetailDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class OrderController {
    @Autowired
    private OrderImpl orderService;

    @GetMapping("/orders")
    @Dto(value = OrderDetailDTO.class)
    public List<OrderDetail> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/orders/{id}")
    @Dto(value = OrderDetailDTO.class)
    public OrderDetail getOrderById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderService.getOrderById(id);
        return orderDetail;
    }

    @PostMapping("/orders")
    public OrderDetail createOrder(@RequestBody OrderDetail orderDetail) throws ResourceNotFoundException {
        OrderDetail o = orderService.createAndUpdate(orderDetail);
        return o;
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDetail> updateOrder(@PathVariable(value = "id") String id, @Valid @RequestBody OrderDetail orderDetail1) throws ResourceNotFoundException {
        OrderDetail o = orderService.createAndUpdate(orderDetail1);
        return ResponseEntity.ok(o);

    }

    @DeleteMapping("/orders/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        boolean order = orderService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", order);
        return response;
    }

    /*
        tìm danh sách KH đã mua 1 sản phẩm bất kỳ
    * */
    @GetMapping("/customer")
    @Dto(value = CustomerDTO.class)
    public List<Customer> getOrdersByFilter(@RequestParam(value = "name") String name) throws ResourceNotFoundException {
        List<Customer> cus = orderService.getOrdersByFilter(name);
        return cus;
    }

    /*
        Danh sách sản phẩm KH đã thêm vào giỏ hàng mà chưa thanh toán
    * */
    @GetMapping("/orders/customer")
    @Dto(value = OrderDetailDTO.class)
    public List<OrderDetail> getOrderByCustomer(String id) throws ResourceNotFoundException {
        List<OrderDetail> list = orderService.getAllOrderByCustomer(id);
        return list;
    }
}

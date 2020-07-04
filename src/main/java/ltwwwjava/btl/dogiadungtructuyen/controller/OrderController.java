package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.dto.WrapperOrderDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.*;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.repository.OrderRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderDetailService;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderService;
import ltwwwjava.btl.dogiadungtructuyen.service.ProductService;
import ltwwwjava.btl.dogiadungtructuyen.service.UserService;
import ltwwwjava.btl.dogiadungtructuyen.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) throws ResourceNotFoundException {
        String usernameCustomer;
        if (!session.getAttributeNames().hasMoreElements()) {
            usernameCustomer = session.getId();
        } else {
            usernameCustomer = session.getAttribute("mySessionAttribute").toString();
        }
        List<OrderDetail> list = orderDetailService.getAllOrderByCustomer(usernameCustomer);
        WrapperOrderDTO wrapper = new WrapperOrderDTO();
        wrapper.setOrderDetails(list);
        model.addAttribute("listOrder", list);
        model.addAttribute("wrapper", wrapper);
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories", listCat);
        User user = new User();
        user = userService.findByUserName(usernameCustomer);
        model.addAttribute("customer",user);
        double total =0;
        total = wrapper.getOrderDetails().stream().mapToDouble(OrderDetail::getSubtotal).sum();
        model.addAttribute("total",total);
        return "cartt";
    }

    @PostMapping("/cart")
    public String updateCart(Model model, @ModelAttribute() WrapperOrderDTO wrapper) {
        List<OrderDetail> orderDetails = wrapper.getOrderDetails();
        model.addAttribute("wrapper", wrapper);
        orderDetails.forEach(orderDetail -> {
            orderDetailService.updateQuantity(orderDetail.getId(), orderDetail.getQuantity());
        });
        return "redirect:/cart";
    }

    @GetMapping("/list_order")
    public String getAllCart(Model model) throws ResourceNotFoundException {
        List<Order> list = orderRepository.findAllByOrderByBillDateDesc();
        model.addAttribute("listOrder", list);
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories", listCat);
        return "dshdd";
    }

    @PostMapping("/add-cart")
    public String addToCart(Model model, @ModelAttribute("product") Product product, HttpSession session) throws ResourceNotFoundException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBillDate(new Date());
        String usernameCustomer;
        if (!session.getAttributeNames().hasMoreElements()) {
            usernameCustomer = session.getId();
        } else {
            usernameCustomer = session.getAttribute("mySessionAttribute").toString();
        }
        orderDetail.setStatus(0);
        orderDetail.setCustomer(usernameCustomer);
        orderDetail.setProducts(product);
        orderDetail.setQuantity(15);
        orderDetailService.createAndUpdate(orderDetail);
//        productService.createAndUpdate(product);
        return "redirect:/products";

    }

    @PostMapping("/payment")
    public String payment(Model model, HttpSession session, @ModelAttribute("wrapper") WrapperOrderDTO wrapperOrderDTO) throws ResourceNotFoundException {
        if (!session.getAttributeNames().hasMoreElements()) {
            return "redirect:/login";
        }
        double total;
        wrapperOrderDTO.getOrderDetails().forEach(orderDetail -> {
            orderDetail.setStatus(Constants.PAID);
        });
        Order order = new Order();
        order.setOrderDetailDTOS(wrapperOrderDTO.getOrderDetails());
        order.setBillDate(new Date());
        order.setIdCustomer(wrapperOrderDTO.getOrderDetails().get(0).getCustomer());
        total = wrapperOrderDTO.getOrderDetails().stream().mapToDouble(OrderDetail::getSubtotal).sum();
        order.setTotal(total);
        orderService.createAndUpdate(order);
        for (OrderDetail orderDetail:wrapperOrderDTO.getOrderDetails()) {
            orderDetail.setStatus(Constants.PAID);
            orderDetailService.createAndUpdate(orderDetail);
        }
        User user = new User();
        user = userService.findByUserName(wrapperOrderDTO.getOrderDetails().get(0).getCustomer());
        model.addAttribute("customer",user);

        return "redirect:/products";

    }

    @GetMapping("/cart/product/{id}")
    public String addToCart(@PathVariable(value = "id") String id, Model model, HttpSession session) throws ResourceNotFoundException {
        Product product = productService.findById(id);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBillDate(new Date());
        String usernameCustomer;
        if (!session.getAttributeNames().hasMoreElements()) {
            usernameCustomer = session.getId();
        } else {
            usernameCustomer = session.getAttribute("mySessionAttribute").toString();
        }
        orderDetail.setCustomer(usernameCustomer);
        orderDetail.setProducts(product);
        orderDetail.setQuantity(1);
        orderDetail.setStatus(0);
        orderDetailService.createAndUpdate(orderDetail);
        return "redirect:/products";
    }

}

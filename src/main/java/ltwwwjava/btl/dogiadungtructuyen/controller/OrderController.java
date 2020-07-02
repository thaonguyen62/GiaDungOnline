package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderService;
import ltwwwjava.btl.dogiadungtructuyen.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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
    private OrderService orderService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) throws ResourceNotFoundException {
        String usernameCustomer;
        if (!session.getAttributeNames().hasMoreElements()) {
            usernameCustomer = session.getId();
        } else {
            usernameCustomer = session.getAttribute("mySessionAttribute").toString();
        }
        List<OrderDetail> list = orderService.getAllOrderByCustomer(usernameCustomer);
        model.addAttribute("listOrder", list);
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories", listCat);
        return "cartt";
    }
    @GetMapping("/list_order")
    public String getAllCart(Model model) throws ResourceNotFoundException {

        List<OrderDetail> list = orderService.getAll();
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
        orderDetail.setCustomer(usernameCustomer);
        orderDetail.setProducts(product);
        orderDetail.setQuantity(15);
        orderService.createAndUpdate(orderDetail);
//        productService.createAndUpdate(product);
        return "redirect:/products";

    }

    @PostMapping("/payment")
    public String payment(Model model, HttpSession session) throws ResourceNotFoundException {
        if (!session.getAttributeNames().hasMoreElements()) {
            return "redirect:/login";
        }
        List<OrderDetail> list = orderService.getAllOrderByCustomer(session.getId());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(orderDetail -> {
                orderDetail.setCustomer(session.getAttribute("mySessionAttribute").toString());
                try {
                    orderService.createAndUpdate(orderDetail);
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
        return "redirect:/products";

    }
    @GetMapping("/cart/product/{id}")
    public String addToCart(@PathVariable(value = "id") String id, Model model,HttpSession session) throws ResourceNotFoundException {
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
        orderService.createAndUpdate(orderDetail);
        return "redirect:/products";
    }


}

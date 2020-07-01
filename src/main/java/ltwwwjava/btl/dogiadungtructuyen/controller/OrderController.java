package ltwwwjava.btl.dogiadungtructuyen.controller;

import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Category;
import ltwwwjava.btl.dogiadungtructuyen.model.OrderDetail;
import ltwwwjava.btl.dogiadungtructuyen.model.Product;
import ltwwwjava.btl.dogiadungtructuyen.repository.CategoryRepository;
import ltwwwjava.btl.dogiadungtructuyen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/cart")
    public String getCart(Model model) throws ResourceNotFoundException {
        List<OrderDetail> list = orderService.getAll();
        model.addAttribute("listOrder", list);
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("categories", listCat);
        return "cartt";
    }

    @PostMapping("/add-cart")
    public String addToCart(Model model, @ModelAttribute("product") Product product) throws ResourceNotFoundException {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBillDate(new Date());
        orderDetail.setCustomer("1");
        orderDetail.setProducts(product.getId());
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

        return "redirect:/products";

    }
}

package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.UserDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.User;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserAPI {

//    @Autowired
//    private UserImpl customerService;
//
//    @GetMapping("/customers")
//    @Dto(value = UserDTO.class)
//    public List<User> getAllCustomer() {
//        return customerService.getAllCustomer();
//    }
//
//    @GetMapping("/customers/{id}")
//    @Dto(value = UserDTO.class)
//    public ResponseEntity<User> getCustomerById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
//        User user = customerService.findById(id);
//        return ResponseEntity.ok().body(user);
//    }
//
//    @PostMapping("/customers")
//    public User createCustomer(@RequestBody User user) throws ResourceNotFoundException {
//        User cus = customerService.createAndUpdate(user);
//        return cus;
//    }
//
//    /*
//        không update được khi không cập nhật hết dữ liệu
//    * */
//    @PutMapping("/customers/{id}")
//    public ResponseEntity<User> updateCustomer(@Valid @RequestBody User cus, @PathVariable(value = "id") String id) throws ResourceNotFoundException {
//        User user = customerService.createAndUpdate(cus);
//        return ResponseEntity.ok(user);
//    }
//
//    @DeleteMapping("/customers/{id}")
//    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
//        boolean cus = customerService.deleteCustomerById(id);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
//
//    @GetMapping("/customer-add")
//    public List<User> getCustomersByAdd(@RequestParam(value = "add") String add) throws ResourceNotFoundException {
//        List<User> cus = customerService.findByAddress(add);
//        return cus;
//
//    }

}

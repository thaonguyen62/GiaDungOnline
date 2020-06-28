package ltwwwjava.btl.dogiadungtructuyen.api;

import ltwwwjava.btl.dogiadungtructuyen.controllerAdvice.Dto;
import ltwwwjava.btl.dogiadungtructuyen.dto.CustomerDTO;
import ltwwwjava.btl.dogiadungtructuyen.exception.ResourceNotFoundException;
import ltwwwjava.btl.dogiadungtructuyen.model.Customer;
import ltwwwjava.btl.dogiadungtructuyen.service.impl.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerAPI {

    @Autowired
    private CustomerImpl customerService;

    @GetMapping("/customers")
    @Dto(value = CustomerDTO.class)
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/customers/{id}")
    @Dto(value = CustomerDTO.class)
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) throws ResourceNotFoundException {
        Customer cus = customerService.createAndUpdate(customer);
        return cus;
    }

    /*
        không update được khi không cập nhật hết dữ liệu
    * */
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer cus, @PathVariable(value = "id") String id) throws ResourceNotFoundException {
        Customer customer = customerService.createAndUpdate(cus);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
        boolean cus = customerService.deleteCustomerById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/customer-add")
    public List<Customer> getCustomersByAdd(@RequestParam(value = "add") String add) throws ResourceNotFoundException {
        List<Customer> cus = customerService.findByAddress(add);
        return cus;

    }

}

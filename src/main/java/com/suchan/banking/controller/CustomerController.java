package com.suchan.banking.controller;

import com.suchan.banking.dto.CreateCustomerRequest;
import com.suchan.banking.dto.CreateCustomerResponse;
import com.suchan.banking.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // @RequestBody: 클라이언트가 보낸 JSON을 CreateCustomerRequest 객체로 바꿔준다.
    @PostMapping("/customers")
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/customers/{id}")
    public CreateCustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/customers/{id}")
    public CreateCustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CreateCustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


}

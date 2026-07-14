package com.suchan.banking.controller;

import com.suchan.banking.dto.CreateCustomerRequest;
import com.suchan.banking.dto.CreateCustomerResponse;
import com.suchan.banking.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

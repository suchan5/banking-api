package com.suchan.banking.service;

import com.suchan.banking.dto.CreateCustomerRequest;
import com.suchan.banking.dto.CreateCustomerResponse;
import com.suchan.banking.entity.Customer;
import com.suchan.banking.exception.CustomerNotFoundException;
import com.suchan.banking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerService {


    private final CustomerRepository customerRepository; // 나중에 다른 Repository로 바뀌면 안 되니까 final을 붙이는 거야.

    // 이게 생성자 주입(Constructor Injection) 이야.
    public CustomerService(CustomerRepository customerRepository) { // Spring이 실행될 때 "CustomerRepository 만들어놨으니까 CustomerService에 넣어줄게."
        this.customerRepository = customerRepository;               // 그래서 Service 안에서 customerRepository.save(...) 를 사용할 수 있는 거야.
    }

    //고객 생성 결과를 클라이언트에게 돌려줘야 하니까 반환 타입은 CreateCustomerResponse
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = Customer.builder() //클라이언트가 보낸 request에서 name, email을 꺼내 Customer Entity를 만들어.
                .name(request.getName())       // id는 db가 만들어주니까 넣지 않는다
                .email(request.getEmail())
                .build();

        Customer savedCustomer = customerRepository.save(customer); // Repository의 save()를 호출하면 저장된 Customer Entity가 반환돼.

        //그다음 저장된 Entity를 Response DTO로 변환해 반환해.
        return CreateCustomerResponse.builder()
                .id(savedCustomer.getId())
                .name(savedCustomer.getName())
                .email(savedCustomer.getEmail())
                .build();

    }

    public CreateCustomerResponse getCustomerById(Long id) {
        Customer customerFoundById = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return CreateCustomerResponse.builder()
                .id(customerFoundById.getId())
                .name(customerFoundById.getName())
                .email(customerFoundById.getEmail())
                .build();
    }

    public CreateCustomerResponse updateCustomer(Long id, CreateCustomerRequest request) {
        Customer customerFoundById = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        // DB에서 찾은 Customer Entity의 값을 수정
        customerFoundById.setName(request.getName());
        customerFoundById.setEmail(request.getEmail());

        Customer updatedCustomer = customerRepository.save(customerFoundById);

        return CreateCustomerResponse.builder()
                .id(updatedCustomer.getId())
                .name(updatedCustomer.getName())
                .email(updatedCustomer.getEmail())
                .build();
    }

    public void deleteCustomer (Long id) {
        Customer customerFoundById = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRepository.delete(customerFoundById);
    }

    public List<CreateCustomerResponse> getAllCustomers() {
        List<Customer> customersFound = customerRepository.findAll();

        List<CreateCustomerResponse> customerResponses = new ArrayList<>();

        for (Customer customer : customersFound) {
            CreateCustomerResponse response = CreateCustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .build();

            customerResponses.add(response);
        }

        return customerResponses;
    }

}

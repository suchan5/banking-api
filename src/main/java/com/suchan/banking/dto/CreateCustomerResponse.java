package com.suchan.banking.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerResponse {
    private Long id;
    private String name;
    private String email;
}

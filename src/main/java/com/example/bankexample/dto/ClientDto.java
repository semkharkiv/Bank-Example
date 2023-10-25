package com.example.bankexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String id;
    private String clientStatus;
    private String firstName;
    private String lastName;
    private String email;
}

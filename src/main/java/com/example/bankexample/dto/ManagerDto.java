package com.example.bankexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
    private String firstName;
    private String lastName;
    private String managerStatus;

}

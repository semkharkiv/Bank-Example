package com.example.bankexample.service;

import com.example.bankexample.dto.ManagerDto;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    List<ManagerDto> getAllManagers();

    Optional<ManagerDto> getManagerWithName(String name);

}

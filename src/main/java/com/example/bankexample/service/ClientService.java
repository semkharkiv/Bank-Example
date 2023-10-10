package com.example.bankexample.service;

import com.example.bankexample.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllActiveClienDtos();
}

package com.example.bankexample.controller;

import com.example.bankexample.dto.ClientDto;
import com.example.bankexample.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/all-active")
    public List<ClientDto> getAllActiveClientDto(){
        return clientService.getAllActiveClienDtos();
    }
}

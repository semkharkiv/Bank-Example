package com.example.bankexample.controller;

import com.example.bankexample.dto.ClientDto;
import com.example.bankexample.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/all-active")
    public ResponseEntity<List<ClientDto>> getAllActiveClientDto() {
        return new ResponseEntity<>(clientService.getAllActiveClienDtos(), HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<ClientDto>>getAllClientsByStatus(@PathVariable("status") String status){
        return new ResponseEntity<>(clientService.getAllClientsByClientStatus(status),HttpStatus.OK);
    }
}

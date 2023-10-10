package com.example.bankexample.service.impl;

import com.example.bankexample.dto.ClientDto;
import com.example.bankexample.mapper.ClientMapper;
import com.example.bankexample.repository.ClientRepository;
import com.example.bankexample.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Override
    @Transactional
    public List<ClientDto> getAllActiveClienDtos() {
        return clientMapper.toDtoList(clientRepository.findAllActiveClients());
    }
}

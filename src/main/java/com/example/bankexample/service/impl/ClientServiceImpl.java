package com.example.bankexample.service.impl;

import com.example.bankexample.dto.ClientDto;
import com.example.bankexample.entity.enums.ClientStatus;
import com.example.bankexample.mapper.ClientMapper;
import com.example.bankexample.repository.ClientRepository;
import com.example.bankexample.service.ClientService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    /**
     * Получает список всех активных клиентов в виде {@link ClientDto}.
     *
     * @return Список {@link ClientDto} для всех активных клиентов.
     */
    @Override
    @Transactional
    public List<ClientDto> getAllActiveClienDtos() {
        return clientMapper.toDtoList(clientRepository.findAllActiveClients());
    }

    /**
     * Получает список клиентов по их статусу в виде {@link ClientDto}.
     *
     * @param status Статус клиента (например, "ACTIVE" или "INACTIVE").
     * @return Список {@link ClientDto} для клиентов с указанным статусом.
     * @throws IllegalArgumentException Если указанный статус некорректен.
     */
    @Override
    @Transactional
    public List<ClientDto> getAllClientsByClientStatus(String status){
        return clientMapper.toDtoList(clientRepository
                .findClientsByClientStatus(ClientStatus.valueOf(status.toUpperCase())));
    }
}

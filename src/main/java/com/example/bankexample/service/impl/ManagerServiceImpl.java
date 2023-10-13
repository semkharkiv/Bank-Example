package com.example.bankexample.service.impl;

import com.example.bankexample.dto.ManagerDto;
import com.example.bankexample.entity.Manager;
import com.example.bankexample.mapper.ManagerMapper;
import com.example.bankexample.repository.ManagerRepository;
import com.example.bankexample.service.ManagerService;
import com.example.bankexample.service.exception.ErrorMessage;
import com.example.bankexample.service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    @Override
    @Transactional
    public List<ManagerDto> getAllManagers() {
        return managerMapper.toDtoList(managerRepository.findAll());
    }

    @Override
    @Transactional
    public Optional<ManagerDto> getManagerWithName(String name) {
        Optional<Manager> optionalManager = managerRepository.findManagerByFirstName(name);
        return optionalManager.map(manager -> Optional.of(managerMapper.toDto(manager)))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MANAGER_NOT_FOUND_BY_NAME));
    }
}

package com.example.bankexample.service.impl;

import com.example.bankexample.dto.ManagerDto;
import com.example.bankexample.mapper.ManagerMapper;
import com.example.bankexample.repository.ManagerRepository;
import com.example.bankexample.service.ManagerService;
import com.example.bankexample.exception.ErrorMessage;
import com.example.bankexample.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    /**
     * Получает список всех менеджеров в виде {@link ManagerDto}.
     *
     * @return Список {@link ManagerDto} для всех менеджеров.
     */
    @Override
    @Transactional
    public List<ManagerDto> getAllManagers() {
        return managerMapper.toDtoList(managerRepository.findAll());
    }

    /**
     * Получает менеджера по его имени в виде {@link ManagerDto}.
     *
     * @param name Имя менеджера.
     * @return {@link ManagerDto} для менеджера с указанным именем.
     * @throws NotFoundException Если менеджер с указанным именем не найден.
     */
    @Override
    @Transactional
    public ManagerDto getManagerWithName(String name) {
        return managerRepository.findManagerByFirstName(name).map(managerMapper::toDto)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MANAGER_NOT_FOUND_BY_NAME));
    }
}

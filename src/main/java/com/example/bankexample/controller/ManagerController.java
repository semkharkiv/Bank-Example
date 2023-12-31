package com.example.bankexample.controller;

import com.example.bankexample.dto.ManagerDto;
import com.example.bankexample.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    /**
     * Получить список всех менеджеров.
     * @return Объект ResponseEntity с списком ManagerDto и статусом OK.
     */
    @GetMapping("/all")
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }

    /**
     * Получить менеджера по имени.
     * @param name Имя менеджера.
     * @return Объект ResponseEntity с ManagerDto и статусом OK.
     */
    @GetMapping("/{name}")
    public ResponseEntity<ManagerDto> getManagerWithName(@PathVariable("name") String name) {
        return new ResponseEntity<>(managerService.getManagerWithName(name), HttpStatus.OK);
    }
}

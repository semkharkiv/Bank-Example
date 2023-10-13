package com.example.bankexample.controller;

import com.example.bankexample.dto.ManagerDto;
import com.example.bankexample.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    @GetMapping("/all")
    public List<ManagerDto> getAllManagers(){
        return managerService.getAllManagers();
    }

    @GetMapping("/{name}")
    public Optional<ManagerDto> getManagerWithName(@PathVariable("name") String name){
        return managerService.getManagerWithName(name);
    }
}

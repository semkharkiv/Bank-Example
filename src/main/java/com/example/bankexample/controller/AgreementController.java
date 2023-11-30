package com.example.bankexample.controller;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/agreements")
@RequiredArgsConstructor
public class AgreementController {

    public final AgreementService agreementService;

    /**
     * Обработчик HTTP GET запроса для получения информации о договоре по его идентификатору.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AgreementDto> getAgreementDtoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(agreementService.getAgreementDtoById(id), HttpStatus.OK);
    }

    /**
     * Обработчик HTTP DELETE запроса для удаления договора по его идентификатору.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAgreementById(@PathVariable("id") Long id) {
        agreementService.deleteAgreementById(id);
    }

    /**
     * Обработчик HTTP POST запроса для создания нового договора.
     */
    @PostMapping
    public ResponseEntity<AgreementDto> createAgreement(@RequestBody AgreementDto agreementDto) {
        return new ResponseEntity<>(agreementService.createAgreement(agreementDto), HttpStatus.CREATED);
    }
}

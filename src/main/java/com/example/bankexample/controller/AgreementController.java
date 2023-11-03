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

    @GetMapping("/{id}")
    public ResponseEntity<AgreementDto> getAgreementDtoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(agreementService.getAgreementDtoById(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAgreementById(@PathVariable("id") Long id) {
        agreementService.deleteAgreementById(id);
    }

    @PostMapping
    public ResponseEntity<AgreementDto> createAgreement(@RequestBody AgreementDto agreementDto) {
        return new ResponseEntity<>(agreementService.createAgreement(agreementDto), HttpStatus.CREATED);
    }

}

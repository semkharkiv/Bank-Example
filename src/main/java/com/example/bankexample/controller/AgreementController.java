package com.example.bankexample.controller;

import com.example.bankexample.dto.AgreementDto;
import com.example.bankexample.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/agreements")
@RequiredArgsConstructor
public class AgreementController {

    public final AgreementService agreementService;

    @GetMapping("/{id}")
    public AgreementDto getAgreementDtoById(@PathVariable ("id") Long id){return agreementService.getAgreementDtoById(id);}

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAgreementById(@PathVariable ("id") Long id){
        agreementService.deleteAgreementById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AgreementDto createAgreement(@RequestBody AgreementDto agreementDto){
        return agreementService.createAgreement(agreementDto);
    }

}

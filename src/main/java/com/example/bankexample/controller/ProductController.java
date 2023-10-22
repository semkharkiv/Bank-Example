package com.example.bankexample.controller;

import com.example.bankexample.dto.ProductDto;
import com.example.bankexample.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{name}")
    ResponseEntity<List<ProductDto>> getProductWithManagerName(@PathVariable("name") String name){
        return new ResponseEntity<>(productService.getProductWithManagerName(name), HttpStatus.OK);
    }
}

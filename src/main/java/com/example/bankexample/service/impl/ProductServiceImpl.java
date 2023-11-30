package com.example.bankexample.service.impl;

import com.example.bankexample.dto.ProductDto;
import com.example.bankexample.entity.Product;
import com.example.bankexample.mapper.ProductMapper;
import com.example.bankexample.repository.ProductRepository;
import com.example.bankexample.service.ProductService;
import com.example.bankexample.exception.ErrorMessage;
import com.example.bankexample.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Получает список продуктов для менеджера с указанным именем в виде {@link ProductDto}.
     *
     * @param name Имя менеджера.
     * @return Список {@link ProductDto} для продуктов, связанных с менеджером с указанным именем.
     * @throws NotFoundException Если менеджер с указанным именем не найден.
     */
    @Override
    @Transactional
    public List<ProductDto> getProductWithManagerName(String name) {
        List<Product> products = productRepository.findProductsByManagerFirstName(name);
        if (products.isEmpty()){throw new NotFoundException(ErrorMessage.MANAGER_NOT_FOUND_BY_NAME);}
        return productMapper.toDtoList(products);
    }
}

package com.example.bankexample.mapper;

import com.example.bankexample.dto.ProductDto;
import com.example.bankexample.entity.Product;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Named("toDto")
    ProductDto toDto(Product product);

    @IterableMapping(qualifiedByName = "toDto")
    List<ProductDto> toDtoList(List<Product> products);
}

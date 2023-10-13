package com.example.bankexample.mapper;

import com.example.bankexample.dto.ManagerDto;
import com.example.bankexample.entity.Manager;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    @Named("toDto")
    ManagerDto toDto(Manager manager);

    @IterableMapping(qualifiedByName = "toDto")
    List<ManagerDto> toDtoList(List<Manager> managers);
}

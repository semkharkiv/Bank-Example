package com.example.bankexample.mapper;

import com.example.bankexample.dto.ClientDto;
import com.example.bankexample.entity.Client;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Named("toDto")
    ClientDto toDto(Client client);

    @IterableMapping(qualifiedByName = "toDto")
    List<ClientDto> toDtoList(List<Client> clients);
}

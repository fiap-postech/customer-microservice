package br.com.fiap.tech.challenge.driven.mysql.mapping;

import br.com.fiap.tech.challenge.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DBCustomerMapper {
    @Mapping(target = "id", source = "uuid")
    CustomerDTO toDTO(CustomerEntity entity);

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "version", ignore = true)
    CustomerEntity toEntity(CustomerDTO dto);
}

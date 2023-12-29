package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.application.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CreateCustomerMapper {

    CreateCustomerMapper INSTANCE = Mappers.getMapper(CreateCustomerMapper.class);

    CreateCustomerDTO toDTO(CreateCustomerRequest request);

}

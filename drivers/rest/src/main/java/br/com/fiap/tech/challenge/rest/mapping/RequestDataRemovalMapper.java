package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.application.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.rest.resource.request.DataRemovalRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RequestDataRemovalMapper {

    RequestDataRemovalDTO toDTO(DataRemovalRequest request);

}

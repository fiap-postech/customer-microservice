package br.com.fiap.tech.challenge.customer.driven.customer.data.removal.response.consumer.mapping;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;
import br.com.fiap.tech.challenge.application.dto.UpdateDataRemovalDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UpdateDataRemovalMapper {

    UpdateDataRemovalDTO toUpdateDTO(DataRemovalDoneDTO dto);

}

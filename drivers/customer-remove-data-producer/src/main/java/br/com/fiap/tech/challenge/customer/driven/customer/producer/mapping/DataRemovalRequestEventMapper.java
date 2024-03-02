package br.com.fiap.tech.challenge.customer.driven.customer.producer.mapping;

import br.com.fiap.tech.challenge.adapter.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.dto.DataRemovalRequestEvent;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DataRemovalRequestEventMapper {

    DataRemovalRequestEvent toEvent(RequestDataRemovalDTO dto);


}

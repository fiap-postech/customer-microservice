package br.com.fiap.tech.challenge.customer.driven.customer.producer.mapping;

import br.com.fiap.tech.challenge.application.dto.DataRemovalItemDTO;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.dto.DataRemovalDoneEvent;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DataRemovalDoneMapper {

    DataRemovalDoneEvent toEvent(DataRemovalItemDTO item);

}

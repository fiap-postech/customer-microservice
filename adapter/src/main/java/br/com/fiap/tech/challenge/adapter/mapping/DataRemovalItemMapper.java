package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.application.dto.DataRemovalItemDTO;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemovalItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonMapper.class})
public interface DataRemovalItemMapper {

    DataRemovalItemMapper INSTANCE = Mappers.getMapper(DataRemovalItemMapper.class);

    @Mapping(target = "id", expression = "java(item.uuid().toString())")
    @Mapping(target = "application", expression = "java(item.application())")
    @Mapping(target = "status", expression = "java(item.status())")
    @Mapping(target = "requested", expression = "java(item.requested())")
    DataRemovalItemDTO toDTO(DataRemovalItem item);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    DataRemovalItem toDomain(DataRemovalItemDTO dto);

}

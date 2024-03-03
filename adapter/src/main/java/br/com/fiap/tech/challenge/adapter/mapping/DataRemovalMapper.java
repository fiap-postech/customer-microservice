package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.DataRemovalItemDTO;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CommonMapper.class, DataRemovalItemMapper.class})
public interface DataRemovalMapper {

    DataRemovalMapper INSTANCE = Mappers.getMapper(DataRemovalMapper.class);

    @Mapping(target = "id", expression = "java(data.uuid().toString())")
    @Mapping(target = "customerId", expression = "java(data.customerId().toString())")
    @Mapping(target = "status", expression = "java(data.status())")
    @Mapping(target = "requested", expression = "java(data.requested())")
    @Mapping(target = "items", source = "data", qualifiedByName = "fromDomainListToDTOList")
    DataRemovalDTO toDTO(DataRemoval data);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "customerId", source = "customerId", qualifiedByName = "generateUuid")
    DataRemoval toDomain(DataRemovalDTO dto);

    @Named("fromDomainListToDTOList")
    static List<DataRemovalItemDTO> fromDomainListToDTOList(DataRemoval data) {
        return data.items()
                .stream()
                .map(DataRemovalItemMapper.INSTANCE::toDTO)
                .toList();
    }

}

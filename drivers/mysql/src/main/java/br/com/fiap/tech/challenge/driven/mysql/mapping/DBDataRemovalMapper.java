package br.com.fiap.tech.challenge.driven.mysql.mapping;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.DataRemovalItemDTO;
import br.com.fiap.tech.challenge.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.driven.mysql.model.DataRemovalEntity;
import br.com.fiap.tech.challenge.driven.mysql.model.DataRemovalItem;
import br.com.fiap.tech.challenge.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class DBDataRemovalMapper {

    @Autowired
    private CustomerEntityRepository customerRepository;


    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "customerId", source = "customer.uuid")
    public abstract DataRemovalDTO toDTO(DataRemovalEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "customer", source = "customerId", qualifiedByName = "getCustomerEntity")
    public abstract DataRemovalEntity toEntity(DataRemovalDTO dto);

    @Mapping(target = "id", source = "uuid")
    public abstract DataRemovalItemDTO toDTO(DataRemovalItem item);

    @Mapping(target = "uuid", source = "id")
    public abstract DataRemovalItem toEntity(DataRemovalItemDTO dto);

    @Named("getCustomerEntity")
    CustomerEntity getCustomerEntity(String id) {
        return customerRepository.findByUuid(id)
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND));
    }


}

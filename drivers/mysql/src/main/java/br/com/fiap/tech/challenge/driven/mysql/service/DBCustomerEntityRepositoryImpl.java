package br.com.fiap.tech.challenge.driven.mysql.service;

import br.com.fiap.tech.challenge.driven.mysql.mapping.DBCustomerMapper;
import br.com.fiap.tech.challenge.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;

@Service
@RequiredArgsConstructor
public class DBCustomerEntityRepositoryImpl implements CustomerWriterRepository, CustomerReaderRepository {

    private final CustomerEntityRepository repository;
    private final DBCustomerMapper mapper;

    @Override
    public CustomerDTO readById(String id) {
        var entity = repository.findByUuid(id)
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, id));

        return mapper.toDTO(entity);
    }

    @Override
    public Optional<CustomerDTO> readByDocument(String document) {
        return repository.findByDocumentAndEnabled(document, Boolean.TRUE)
                .map(mapper::toDTO);
    }

    @Override
    public CustomerDTO write(CustomerDTO customer) {
        if (repository.existsByUuid(customer.getId())){
            return update(customer);
        }

        return insert(customer);
    }

    private CustomerDTO insert(CustomerDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    private CustomerDTO update(CustomerDTO dto) {
        var customer = loadByUUID(dto.getId());

        customer.setName(dto.getName());
        customer.setEnabled(dto.isEnabled());
        customer.setEmail(dto.getEmail());

        return mapper.toDTO(repository.save(customer));
    }

    private CustomerEntity loadByUUID(String uuid) {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, uuid));
    }
}
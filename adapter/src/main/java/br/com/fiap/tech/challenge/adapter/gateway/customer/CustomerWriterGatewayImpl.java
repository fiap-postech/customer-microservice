package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.mapping.CustomerMapper;
import br.com.fiap.tech.challenge.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CustomerWriterGatewayImpl implements CustomerWriterGateway {

    private final CustomerWriterRepository writerRepository;

    private static final CustomerMapper MAPPER = CustomerMapper.INSTANCE;


    @Override
    public Customer write(Customer customer) {
        return MAPPER.toDomain(writerRepository.write(MAPPER.toDTO(customer)));
    }
}

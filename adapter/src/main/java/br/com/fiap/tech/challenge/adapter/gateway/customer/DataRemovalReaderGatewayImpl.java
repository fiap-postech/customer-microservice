package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.mapping.DataRemovalMapper;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class DataRemovalReaderGatewayImpl implements DataRemovalReaderGateway {

    private final DataRemovalReaderRepository repository;

    @Override
    public Optional<DataRemoval> readById(UUID id) {
        return repository.readById(id.toString())
                .map(DataRemovalMapper.INSTANCE::toDomain);
    }

    @Override
    public Optional<DataRemoval> readByCustomerId(UUID id) {
        return repository.readByCustomerId(id.toString())
                .map(DataRemovalMapper.INSTANCE::toDomain);
    }
}

package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.mapping.DataRemovalMapper;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalWriterRepository;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DataRemovalWriterGatewayImpl implements DataRemovalWriterGateway {

    private final DataRemovalWriterRepository repository;

    @Override
    public DataRemoval write(DataRemoval data) {
        var mapper = DataRemovalMapper.INSTANCE;

        return mapper.toDomain(repository.write(mapper.toDTO(data)));
    }
}

package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class FindDataRemovalByUUIDUseCaseImpl implements FindDataRemovalByUUIDUseCase {

    private final DataRemovalReaderGateway gateway;

    @Override
    public Optional<DataRemoval> get(UUID uuid) {
        return gateway.readById(uuid);
    }
}

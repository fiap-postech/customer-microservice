package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

import java.util.Optional;
import java.util.UUID;

public interface DataRemovalReaderGateway {
    Optional<DataRemoval> readById(UUID id);

    Optional<DataRemoval> readByCustomerId(UUID id);
}

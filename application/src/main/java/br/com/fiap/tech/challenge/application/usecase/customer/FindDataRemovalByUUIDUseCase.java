package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

import java.util.Optional;
import java.util.UUID;

public interface FindDataRemovalByUUIDUseCase {

    Optional<DataRemoval> get(UUID uuid);

}
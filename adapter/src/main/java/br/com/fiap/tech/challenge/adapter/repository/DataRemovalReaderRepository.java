package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;

import java.util.Optional;

public interface DataRemovalReaderRepository {
    Optional<DataRemovalDTO> readById(String id);

    Optional<DataRemovalDTO> readByCustomerId(String id);
}

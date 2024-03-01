package br.com.fiap.tech.challenge.adapter.controller.customer;


import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;

import java.util.Optional;

public interface FindDataRemovalByUUIDController {

    Optional<DataRemovalDTO> get(String uuid);
}

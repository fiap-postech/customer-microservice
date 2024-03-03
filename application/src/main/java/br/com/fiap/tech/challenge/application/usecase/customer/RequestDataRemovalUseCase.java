package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

public interface RequestDataRemovalUseCase {

    DataRemoval create(RequestDataRemovalDTO dto);
}

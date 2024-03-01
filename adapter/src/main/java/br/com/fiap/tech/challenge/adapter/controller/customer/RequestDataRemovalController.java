package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.RequestDataRemovalDTO;

public interface RequestDataRemovalController {

    DataRemovalDTO create(RequestDataRemovalDTO dto);
}

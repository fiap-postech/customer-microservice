package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;

public interface PublishDataRemovalResponseGateway {
    void publishResponse(DataRemovalDoneDTO dto);
}

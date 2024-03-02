package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.DataRemovalItem;

public interface PublishDataRemovalResponseGateway {
    void publishResponse(DataRemovalItem item);
}

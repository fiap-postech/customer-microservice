package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemovalItem;

public interface PublishDataRemovalUseCase {

    void publishRequest(DataRemoval data);

    void publishResponse(DataRemovalItem item);

}

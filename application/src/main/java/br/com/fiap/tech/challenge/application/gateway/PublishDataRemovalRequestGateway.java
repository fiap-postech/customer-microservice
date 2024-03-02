package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

public interface PublishDataRemovalRequestGateway {

    void publishRequest(DataRemoval dto, Customer customer);
}

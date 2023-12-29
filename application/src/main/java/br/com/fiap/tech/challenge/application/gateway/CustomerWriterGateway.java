package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;

public interface CustomerWriterGateway {
    Customer write(Customer customer);
}

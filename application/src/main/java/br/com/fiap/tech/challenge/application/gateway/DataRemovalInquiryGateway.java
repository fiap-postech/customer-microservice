package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

public interface DataRemovalInquiryGateway {

    void write(DataRemoval dataRemoval, Customer customer);

}

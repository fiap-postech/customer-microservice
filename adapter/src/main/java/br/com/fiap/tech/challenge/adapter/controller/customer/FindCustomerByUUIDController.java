package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;

public interface FindCustomerByUUIDController {
    CustomerDTO get(String uuid);
}

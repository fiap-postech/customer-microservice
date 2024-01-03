package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;

import java.util.Optional;

public interface FindCustomerByUUIDController {
    Optional<CustomerDTO> get(String uuid);
}

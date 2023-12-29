package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;

import java.util.Optional;

public interface UpgradeCustomerController {
    Optional<CustomerDTO> disable(String document);
}

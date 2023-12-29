package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;

import java.util.Optional;

public interface UpgradeCustomerUseCase {

    Optional<Customer> disable(String document);
}

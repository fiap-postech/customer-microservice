package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;

public interface CreateCustomerUseCase {
    Customer create(CreateCustomerDTO dto);
}

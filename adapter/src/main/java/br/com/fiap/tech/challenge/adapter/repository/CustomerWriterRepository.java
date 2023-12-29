package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;

public interface CustomerWriterRepository {
    CustomerDTO write(CustomerDTO customer);
}

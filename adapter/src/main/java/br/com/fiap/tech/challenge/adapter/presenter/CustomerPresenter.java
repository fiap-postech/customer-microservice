package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;

public interface CustomerPresenter {
    CustomerDTO present(Customer customer);
}

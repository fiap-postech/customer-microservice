package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.mapping.CustomerMapper;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;

class CustomerPresenterImpl implements CustomerPresenter {
    @Override
    public CustomerDTO present(Customer customer) {
        return CustomerMapper.INSTANCE.toDTO(customer);
    }

}

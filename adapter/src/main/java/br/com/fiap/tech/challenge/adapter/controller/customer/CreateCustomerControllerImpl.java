package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.application.usecase.customer.CreateCustomerUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreateCustomerControllerImpl implements CreateCustomerController {

    private final CreateCustomerUseCase useCase;
    private final CustomerPresenter presenter;

    @Override
    public CustomerDTO create(CreateCustomerDTO dto) {
        return presenter.present(useCase.create(dto));
    }
}

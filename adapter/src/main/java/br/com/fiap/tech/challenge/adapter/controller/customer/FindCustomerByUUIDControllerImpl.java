package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class FindCustomerByUUIDControllerImpl implements FindCustomerByUUIDController {

    private final FindCustomerByUUIDUseCase useCase;
    private final CustomerPresenter presenter;

    @Override
    public CustomerDTO get(String uuid) {
        return presenter.present(useCase.get(UUID.fromString(uuid)));
    }
}

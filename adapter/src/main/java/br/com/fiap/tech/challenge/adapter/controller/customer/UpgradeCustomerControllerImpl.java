package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.UpgradeCustomerUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class UpgradeCustomerControllerImpl implements UpgradeCustomerController{

    private final UpgradeCustomerUseCase useCase;
    private final CustomerPresenter presenter;

    @Override
    public Optional<CustomerDTO> disable(String document) {
        return useCase.disable(document).map(presenter::present);
    }
}

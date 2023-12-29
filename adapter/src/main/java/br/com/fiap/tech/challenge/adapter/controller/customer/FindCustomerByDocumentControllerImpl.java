package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class FindCustomerByDocumentControllerImpl implements FindCustomerByDocumentController{

    private final FindCustomerByDocumentUseCase useCase;
    private final CustomerPresenter presenter;

    @Override
    public Optional<CustomerDTO> get(String document) {
        return useCase.get(document).map(presenter::present);
    }
}

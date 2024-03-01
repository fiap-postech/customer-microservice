package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.presenter.DataRemovalPresenter;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.application.usecase.customer.RequestDataRemovalUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RequestDataRemovalControllerImpl implements RequestDataRemovalController {

    private final RequestDataRemovalUseCase useCase;
    private final DataRemovalPresenter presenter;

    @Override
    public DataRemovalDTO create(RequestDataRemovalDTO dto) {
        return presenter.present(useCase.create(dto));
    }
}

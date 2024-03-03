package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.presenter.DataRemovalPresenter;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.usecase.customer.FindDataRemovalByUUIDUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class FindDataRemovalByUUIDControllerImpl implements FindDataRemovalByUUIDController {

    private final FindDataRemovalByUUIDUseCase useCase;
    private final DataRemovalPresenter presenter;

    @Override
    public Optional<DataRemovalDTO> get(String uuid) {
        return useCase.get(UUID.fromString(uuid))
                .map(presenter::present);
    }
}

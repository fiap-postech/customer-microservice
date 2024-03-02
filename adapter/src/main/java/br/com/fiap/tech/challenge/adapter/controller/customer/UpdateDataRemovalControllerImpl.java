package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.application.dto.UpdateDataRemovalDTO;
import br.com.fiap.tech.challenge.application.usecase.customer.UpdateDataRemovalUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UpdateDataRemovalControllerImpl implements UpdateDataRemovalController {

    private final UpdateDataRemovalUseCase useCase;

    @Override
    public void update(UpdateDataRemovalDTO dto) {
        useCase.update(dto);
    }
}

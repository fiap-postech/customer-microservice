package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.usecase.customer.RemoveDataUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RemoveDataControllerImpl implements RemoveDataController {

    private final RemoveDataUseCase useCase;

    @Override
    public DataRemovalDTO remove(DataRemovalDTO dto) {
        return useCase.remove(dto);
    }
}

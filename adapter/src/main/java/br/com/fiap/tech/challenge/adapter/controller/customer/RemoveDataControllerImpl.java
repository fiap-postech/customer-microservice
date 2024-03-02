package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.application.dto.ActionDataRemovalDTO;
import br.com.fiap.tech.challenge.application.usecase.customer.RemoveDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class RemoveDataControllerImpl implements RemoveDataController {

    private final RemoveDataUseCase removeUseCase;

    @Override
    @Transactional
    public void remove(ActionDataRemovalDTO dto) {
        removeUseCase.remove(dto);
    }
}

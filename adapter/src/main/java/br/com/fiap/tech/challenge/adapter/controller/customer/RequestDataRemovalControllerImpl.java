package br.com.fiap.tech.challenge.adapter.controller.customer;

import br.com.fiap.tech.challenge.adapter.presenter.DataRemovalPresenter;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.application.usecase.customer.PublishDataRemovalUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.RequestDataRemovalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class RequestDataRemovalControllerImpl implements RequestDataRemovalController {

    private final RequestDataRemovalUseCase removalUseCase;
    private final PublishDataRemovalUseCase publishUseCase;
    private final DataRemovalPresenter presenter;

    @Override
    @Transactional
    public DataRemovalDTO create(RequestDataRemovalDTO dto) {
        var result = removalUseCase.create(dto);

        publishUseCase.publish(result);

        return presenter.present(result);
    }
}

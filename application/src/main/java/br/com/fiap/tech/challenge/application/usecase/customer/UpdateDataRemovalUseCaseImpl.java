package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.UpdateDataRemovalDTO;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemovalItem;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.DATA_REMOVAL_REQUEST_NOT_FOUND;

@RequiredArgsConstructor
class UpdateDataRemovalUseCaseImpl implements UpdateDataRemovalUseCase {

    private final DataRemovalReaderGateway readerGateway;
    private final DataRemovalWriterGateway writerGateway;

    @Override
    public void update(UpdateDataRemovalDTO dto) {
        var removal = readerGateway.readById(UUID.fromString(dto.getId()))
                .orElseThrow(() -> new ApplicationException(DATA_REMOVAL_REQUEST_NOT_FOUND));

        var newItems = new ArrayList<DataRemovalItem>();

        for (var item : removal.items()) {
            if (item.application().equals(dto.getApplication())) {
                newItems.add(item.toBuilder().status(dto.getStatus()).build());
                continue;
            }

            newItems.add(item);
        }

        writerGateway.write(
                removal.toBuilder()
                        .items(newItems)
                        .build()
        );
    }
}

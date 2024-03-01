package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND;

@RequiredArgsConstructor
class RemoveDataUseCaseImpl implements RemoveDataUseCase {

    private final CustomerReaderGateway readerGateway;
    private final CustomerWriterGateway writerGateway;

    @Override
    public DataRemovalDTO remove(DataRemovalDTO dto) {
        var customer = readerGateway.readById(UUID.fromString(dto.getCustomerId()))
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND));

        customer = customer.toBuilder()
                .enabled(false)
                .document(null)
                .email(null)
                .name("CONSUMIDOR - DATA REMOVAL")
                .build();

        writerGateway.write(customer);

        return dto.setStatus(DataRemovalStatus.FINISHED);
    }
}

package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.ActionDataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalResponseGateway;
import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND;

@RequiredArgsConstructor
class RemoveDataUseCaseImpl implements RemoveDataUseCase {

    private final CustomerReaderGateway readerGateway;
    private final CustomerWriterGateway writerGateway;
    private final PublishDataRemovalResponseGateway responseGateway;

    @Override
    public DataRemovalDoneDTO remove(ActionDataRemovalDTO dto) {
        var document = Document.of(dto.getDocument());

        var customer = readerGateway.readByDocument(document)
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND));

        customer = customer.toBuilder()
                .enabled(false)
                .document(Document.of(null))
                .email(EmailRegistration.of("removido@techchallenge.com.br"))
                .name("CONSUMIDOR - DATA REMOVAL")
                .build();

        writerGateway.write(customer);

        var response = new DataRemovalDoneDTO();
        response.setApplication("customer-service");
        response.setId(dto.getId());
        response.setStatus(DataRemovalStatus.FINISHED);

        responseGateway.publishResponse(response);

        return response;
    }
}

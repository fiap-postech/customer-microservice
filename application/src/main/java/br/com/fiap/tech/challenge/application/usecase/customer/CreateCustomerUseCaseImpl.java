package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_HAS_REGISTRATION;


@RequiredArgsConstructor
class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerWriterGateway writerService;
    private final CustomerReaderGateway readerService;

    @Override
    public Customer create(CreateCustomerDTO dto) {
        var document = Document.of(dto.getDocument());

        if (readerService.readByDocument(document).isPresent()){
            throw new ApplicationException(CUSTOMER_HAS_REGISTRATION);
        }

        return createCustomer(dto);
    }

    private Customer createCustomer(CreateCustomerDTO dto) {
        var customer = Customer.builder()
                .email(EmailRegistration.of(dto.getEmail()))
                .document(Document.of(dto.getDocument()))
                .name(dto.getName())
                .enabled(true)
                .build();

        return writerService.write(customer);
    }
}
package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerUseCaseFactory {

    public static CreateCustomerUseCase createCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return new CreateCustomerUseCaseImpl(writer, reader);
    }

    public static UpgradeCustomerUseCase upgradeCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return new UpgradeCustomerUseCaseImpl(writer, reader);
    }

    public static FindCustomerByDocumentUseCase findCustomerByDocumentService(CustomerReaderGateway reader) {
        return new FindCustomerByDocumentUseCaseImpl(reader);
    }

    public static FindCustomerByUUIDUseCase findFindCustomerByUUIDService(CustomerReaderGateway reader) {
        return new FindCustomerByUUIDUseCaseImpl(reader);
    }
}

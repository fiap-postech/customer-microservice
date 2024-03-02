package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalRequestGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalResponseGateway;
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

    public static FindDataRemovalByUUIDUseCase findDataRemovalByUUIDUseCase(DataRemovalReaderGateway gateway) {
        return new FindDataRemovalByUUIDUseCaseImpl(gateway);
    }

    public static RemoveDataUseCase removeDataUseCase(CustomerReaderGateway readerGateway, CustomerWriterGateway writerGateway, PublishDataRemovalResponseGateway responseGateway) {
        return new RemoveDataUseCaseImpl(readerGateway, writerGateway, responseGateway);
    }

    public static RequestDataRemovalUseCase requestDataRemovalUseCase(CustomerReaderGateway customerReaderGateway,
                                                                      DataRemovalReaderGateway removalReaderGateway,
                                                                      DataRemovalWriterGateway removalWriterGateway) {
        return new RequestDataRemovalUseCaseImpl(customerReaderGateway, removalReaderGateway, removalWriterGateway);
    }

    public static PublishDataRemovalUseCase publishDataRemovalUseCase(PublishDataRemovalRequestGateway requestGateway,
                                                                      CustomerReaderGateway customerReaderGateway) {
        return new PublishDataRemovalUseCaseImpl(requestGateway, customerReaderGateway);
    }
}

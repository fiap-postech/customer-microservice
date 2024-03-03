package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalRequestGateway;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND;

@RequiredArgsConstructor
class PublishDataRemovalUseCaseImpl implements PublishDataRemovalUseCase {

    private final PublishDataRemovalRequestGateway requestGateway;
    private final CustomerReaderGateway customerReaderGateway;

    @Override
    public void publish(DataRemoval data) {
        var customer = customerReaderGateway.readById(data.customerId())
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND));

        requestGateway.publishRequest(data, customer);
    }

}

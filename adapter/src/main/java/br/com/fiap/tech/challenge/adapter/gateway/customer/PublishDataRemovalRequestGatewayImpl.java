package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalRequestRepository;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalRequestGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PublishDataRemovalRequestGatewayImpl implements PublishDataRemovalRequestGateway {

    private final PublishDataRemovalRequestRepository requestRepository;

    @Override
    public void publishRequest(DataRemoval data, Customer customer) {
        var dto = new RequestDataRemovalDTO()
                .setDocument(customer.toDocument())
                .setId(data.uuid().toString());

        requestRepository.publish(dto);
    }

}

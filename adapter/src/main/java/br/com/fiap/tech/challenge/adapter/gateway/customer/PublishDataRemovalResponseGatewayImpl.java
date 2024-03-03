package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalResponseRepository;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalResponseGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PublishDataRemovalResponseGatewayImpl implements PublishDataRemovalResponseGateway {

    private final PublishDataRemovalResponseRepository responseRepository;

    @Override
    public void publishResponse(DataRemovalDoneDTO dto) {
        responseRepository.publish(dto);
    }
}

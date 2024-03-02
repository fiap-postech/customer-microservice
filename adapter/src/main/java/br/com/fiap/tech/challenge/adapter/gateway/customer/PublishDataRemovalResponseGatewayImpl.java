package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.mapping.DataRemovalItemMapper;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalResponseRepository;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalResponseGateway;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemovalItem;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PublishDataRemovalResponseGatewayImpl implements PublishDataRemovalResponseGateway {

    private final PublishDataRemovalResponseRepository responseRepository;

    @Override
    public void publishResponse(DataRemovalItem item) {
        responseRepository.publish(DataRemovalItemMapper.INSTANCE.toDTO(item));
    }
}

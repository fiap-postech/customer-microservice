package br.com.fiap.tech.challenge.customer.driven.customer.data.removal.response.consumer.messaging;

import br.com.fiap.tech.challenge.adapter.controller.customer.UpdateDataRemovalController;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;
import br.com.fiap.tech.challenge.customer.driven.customer.data.removal.response.consumer.mapping.UpdateDataRemovalMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.fiap.tech.challenge.customer.driven.customer.data.removal.response.consumer.config.EnvironmentProperties.DATA_REMOVED_RESPONSE_QUEUE;


@Component
@RequiredArgsConstructor
public class CustomerDataRemovedResponseListener {

    private final UpdateDataRemovalController controller;
    private final UpdateDataRemovalMapper mapper;

    @SqsListener(value = "${" + DATA_REMOVED_RESPONSE_QUEUE + "}")
    public void listen(Message<DataRemovalDoneDTO> message) {
        controller.update(mapper.toUpdateDTO(message.getPayload()));
    }

}

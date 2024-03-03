package br.com.fiap.tech.challenge.customer.driven.customer.data.removal.action.consumer.messaging;

import br.com.fiap.tech.challenge.adapter.controller.customer.RemoveDataController;
import br.com.fiap.tech.challenge.application.dto.ActionDataRemovalDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.fiap.tech.challenge.customer.driven.customer.data.removal.action.consumer.config.EnvironmentProperties.DATA_REMOVAL_ACTION_QUEUE;


@Component
@RequiredArgsConstructor
public class CustomerDataRemovalActionListener {

    private final RemoveDataController controller;

    @SqsListener("${" + DATA_REMOVAL_ACTION_QUEUE + "}")
    public void listen(Message<ActionDataRemovalDTO> message) {
        controller.remove(message.getPayload());
    }

}

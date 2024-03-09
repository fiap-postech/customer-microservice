package br.com.fiap.tech.challenge.customer.driven.customer.producer.messaging;

import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalRequestRepository;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalResponseRepository;
import br.com.fiap.tech.challenge.application.dto.ActionDataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.config.EnvironmentProperties;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.dto.EventDTO;
import io.awspring.cloud.sns.core.SnsTemplate;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerDataRemovalProducer implements PublishDataRemovalRequestRepository, PublishDataRemovalResponseRepository {

    @Value("${" + EnvironmentProperties.REQUEST_DATA_REMOVAL_TOPIC + "}")
    private String topicName;

    @Value("${" + EnvironmentProperties.DATA_REMOVAL_DONE_QUEUE + "}")
    private String queueName;

    private final SnsTemplate sns;
    private final SqsTemplate sqs;

    @Override
    public void publish(ActionDataRemovalDTO dto) {
        sns.convertAndSend(topicName, dto);
    }

    @Override
    public void publish(DataRemovalDoneDTO dto) {
        sqs.send(to -> to.queue(queueName).payload(
                new EventDTO()
                        .setApplication(dto.getApplication())
                        .setId(dto.getId())
                        .setStatus(dto.getStatus())
        ));
    }
}

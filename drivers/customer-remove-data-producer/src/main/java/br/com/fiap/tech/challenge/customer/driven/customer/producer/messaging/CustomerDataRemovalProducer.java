package br.com.fiap.tech.challenge.customer.driven.customer.producer.messaging;

import br.com.fiap.tech.challenge.adapter.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalRequestRepository;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalResponseRepository;
import br.com.fiap.tech.challenge.application.dto.DataRemovalItemDTO;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.config.EnvironmentProperties;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.mapping.DataRemovalDoneMapper;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.mapping.DataRemovalRequestEventMapper;
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
    private final DataRemovalRequestEventMapper requestEventMapper;
    private final DataRemovalDoneMapper removalDoneMapper;

    @Override
    public void publish(RequestDataRemovalDTO dto) {
        sns.convertAndSend(topicName, requestEventMapper.toEvent(dto));
    }

    @Override
    public void publish(DataRemovalItemDTO dto) {
        sqs.send(to -> to.queue(queueName).payload(removalDoneMapper.toEvent(dto)));
    }
}

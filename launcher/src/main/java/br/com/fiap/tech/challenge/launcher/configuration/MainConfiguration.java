package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.customer.driven.customer.data.removal.action.consumer.config.DataRemovalActionConsumerConfiguration;
import br.com.fiap.tech.challenge.customer.driven.customer.data.removal.response.consumer.config.DataRemovalResponseConsumerConfiguration;
import br.com.fiap.tech.challenge.customer.driven.customer.inquiry.producer.config.DataRemovalInquiryProducerConfiguration;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.config.CustomerProducerConfiguration;
import br.com.fiap.tech.challenge.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.rest.config.RestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sns.core.TopicArnResolver;
import io.awspring.cloud.sns.core.TopicsListingTopicArnResolver;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
@Import({
        RestConfiguration.class,
        MySQLConfiguration.class,
        CustomerProducerConfiguration.class,
        DataRemovalActionConsumerConfiguration.class,
        DataRemovalResponseConsumerConfiguration.class,
        DataRemovalInquiryProducerConfiguration.class
})
public class MainConfiguration {

    @Bean
    public TopicArnResolver topicArnResolver(SnsClient snsClient) {
        return new TopicsListingTopicArnResolver(snsClient);
    }

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient client, ObjectMapper mapper) {
        return SqsTemplate.builder()
                .sqsAsyncClient(client)
                .configureDefaultConverter(converter -> {
                    converter.setObjectMapper(mapper);
                    converter.setPayloadTypeHeaderValueFunction(m -> null);
                })
                .build();
    }
}

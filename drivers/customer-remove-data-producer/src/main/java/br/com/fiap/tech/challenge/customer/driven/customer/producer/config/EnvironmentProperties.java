package br.com.fiap.tech.challenge.customer.driven.customer.producer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String REQUEST_DATA_REMOVAL_TOPIC = "aws.resources.sns.customer-remove-data.topic";
    public static final String DATA_REMOVAL_DONE_QUEUE = "aws.resources.sqs.customer-removed-data.queue";

}

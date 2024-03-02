package br.com.fiap.tech.challenge.customer.driven.customer.data.removal.response.consumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String DATA_REMOVED_RESPONSE_QUEUE = "aws.resources.sqs.customer-removed-data.queue";

}

package br.com.fiap.tech.challenge.customer.driven.customer.data.removal.action.consumer.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentProperties {

    public static final String DATA_REMOVAL_ACTION_QUEUE = "aws.resources.sqs.remove-customer-data.queue";

}

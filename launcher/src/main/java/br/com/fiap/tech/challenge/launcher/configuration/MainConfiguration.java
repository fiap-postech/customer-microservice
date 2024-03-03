package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.customer.driven.customer.data.removal.action.consumer.config.DataRemovalActionConsumerConfiguration;
import br.com.fiap.tech.challenge.customer.driven.customer.data.removal.response.consumer.config.DataRemovalResponseConsumerConfiguration;
import br.com.fiap.tech.challenge.customer.driven.customer.producer.config.CustomerProducerConfiguration;
import br.com.fiap.tech.challenge.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.rest.config.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestConfiguration.class,
        MySQLConfiguration.class,
        CustomerProducerConfiguration.class,
        DataRemovalActionConsumerConfiguration.class,
        DataRemovalResponseConsumerConfiguration.class
})
public class MainConfiguration {
}

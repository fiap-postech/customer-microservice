package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.customer.driven.customer.producer.config.CustomerProducerConfiguration;
import br.com.fiap.tech.challenge.driven.mysql.config.MySQLConfiguration;
import br.com.fiap.tech.challenge.rest.config.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestConfiguration.class,
        MySQLConfiguration.class,
        CustomerProducerConfiguration.class
})
public class MainConfiguration {
}

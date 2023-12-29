package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.gateway.customer.CustomerGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public CustomerWriterGateway customerWriterGateway(CustomerWriterRepository writerRepository) {
        return CustomerGatewayFactory.customerWriterGateway(writerRepository);
    }

    @Bean
    public CustomerReaderGateway customerReaderGateway(CustomerReaderRepository repository) {
        return CustomerGatewayFactory.customerReaderGateway(repository);
    }

}

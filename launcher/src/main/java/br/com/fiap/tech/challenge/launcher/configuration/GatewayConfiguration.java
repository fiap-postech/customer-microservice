package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.gateway.customer.CustomerGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalRequestRepository;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalResponseRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalRequestGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalResponseGateway;
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

    @Bean
    public DataRemovalWriterGateway dataRemovalWriterGateway(DataRemovalWriterRepository repository) {
        return CustomerGatewayFactory.dataRemovalWriterGateway(repository);
    }

    @Bean
    public DataRemovalReaderGateway dataRemovalReaderGateway(DataRemovalReaderRepository repository) {
        return CustomerGatewayFactory.dataRemovalReaderGateway(repository);
    }

    @Bean
    public PublishDataRemovalRequestGateway publishDataRemovalRequestGateway(PublishDataRemovalRequestRepository requestRepository) {
        return CustomerGatewayFactory.publishDataRemovalRequestGateway(requestRepository);
    }

    @Bean
    public PublishDataRemovalResponseGateway publishDataRemovalResponseGateway(PublishDataRemovalResponseRepository responseRepository) {
        return CustomerGatewayFactory.publishDataRemovalResponseGateway(responseRepository);
    }

}

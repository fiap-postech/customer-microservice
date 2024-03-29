package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalInquiryGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalRequestGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalResponseGateway;
import br.com.fiap.tech.challenge.application.usecase.customer.CreateCustomerUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.CustomerUseCaseFactory;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.FindDataRemovalByUUIDUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.PublishDataRemovalUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.RemoveDataUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.RequestDataRemovalUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.UpdateDataRemovalUseCase;
import br.com.fiap.tech.challenge.application.usecase.customer.UpgradeCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreateCustomerUseCase createCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.createCustomerService(writer, reader);
    }

    @Bean
    public UpgradeCustomerUseCase upgradeCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.upgradeCustomerService(writer, reader);
    }

    @Bean
    public FindCustomerByDocumentUseCase findCustomerByDocumentService(CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.findCustomerByDocumentService(reader);
    }

    @Bean
    public FindCustomerByUUIDUseCase findCustomerByUUIDService(CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.findFindCustomerByUUIDService(reader);
    }

    @Bean
    public FindDataRemovalByUUIDUseCase findDataRemovalByUUIDUseCase(DataRemovalReaderGateway gateway) {
        return CustomerUseCaseFactory.findDataRemovalByUUIDUseCase(gateway);
    }

    @Bean
    public RequestDataRemovalUseCase requestDataRemovalUseCase(CustomerReaderGateway customerReaderGateway,
                                                               DataRemovalReaderGateway removalReaderGateway,
                                                               DataRemovalWriterGateway removalWriterGateway,
                                                               DataRemovalInquiryGateway inquiryGateway) {

        return CustomerUseCaseFactory.requestDataRemovalUseCase(
                customerReaderGateway,
                removalReaderGateway,
                removalWriterGateway,
                inquiryGateway
        );
    }

    @Bean
    public RemoveDataUseCase removeDataUseCase(CustomerReaderGateway readerGateway,
                                               CustomerWriterGateway writerGateway,
                                               PublishDataRemovalResponseGateway responseGateway) {
        return CustomerUseCaseFactory.removeDataUseCase(readerGateway, writerGateway, responseGateway);
    }

    @Bean
    public UpdateDataRemovalUseCase updateDataRemovalUseCase(DataRemovalReaderGateway removalReaderGateway,
                                                             DataRemovalWriterGateway removalWriterGateway) {
        return CustomerUseCaseFactory.updateDataRemovalUseCase(removalReaderGateway, removalWriterGateway);
    }

    @Bean
    public PublishDataRemovalUseCase publishDataRemovalUseCase(PublishDataRemovalRequestGateway requestGateway,
                                                               CustomerReaderGateway customerReaderGateway) {
        return CustomerUseCaseFactory.publishDataRemovalUseCase(requestGateway, customerReaderGateway);
    }
}
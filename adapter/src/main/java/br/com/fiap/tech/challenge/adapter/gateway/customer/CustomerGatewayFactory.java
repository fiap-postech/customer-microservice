package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalInquiryRepository;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalWriterRepository;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalRequestRepository;
import br.com.fiap.tech.challenge.adapter.repository.PublishDataRemovalResponseRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalInquiryGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalWriterGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalRequestGateway;
import br.com.fiap.tech.challenge.application.gateway.PublishDataRemovalResponseGateway;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerGatewayFactory {

    public static CustomerWriterGateway customerWriterGateway(CustomerWriterRepository writerRepository) {
        return new CustomerWriterGatewayImpl(writerRepository);
    }

    public static CustomerReaderGateway customerReaderGateway(CustomerReaderRepository readerRepository){
        return new CustomerReaderGatewayImpl(readerRepository);
    }

    public static DataRemovalWriterGateway dataRemovalWriterGateway(DataRemovalWriterRepository repository) {
        return new DataRemovalWriterGatewayImpl(repository);
    }

    public static DataRemovalReaderGateway dataRemovalReaderGateway(DataRemovalReaderRepository repository) {
        return new DataRemovalReaderGatewayImpl(repository);
    }

    public static PublishDataRemovalRequestGateway publishDataRemovalRequestGateway(PublishDataRemovalRequestRepository requestRepository) {
        return new PublishDataRemovalRequestGatewayImpl(requestRepository);
    }

    public static PublishDataRemovalResponseGateway publishDataRemovalResponseGateway(PublishDataRemovalResponseRepository responseRepository) {
        return new PublishDataRemovalResponseGatewayImpl(responseRepository);
    }

    public static DataRemovalInquiryGateway dataRemovalInquiryGateway(DataRemovalInquiryRepository repository) {
        return new DataRemovalInquiryGatewayImpl(repository);
    }
}

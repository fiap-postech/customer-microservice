package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.CustomerWriterRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
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

}

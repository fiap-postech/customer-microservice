package br.com.fiap.tech.challenge.adapter.gateway.customer;

import br.com.fiap.tech.challenge.adapter.dto.DataRemovalInquiryDTO;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalInquiryRepository;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalInquiryGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class DataRemovalInquiryGatewayImpl implements DataRemovalInquiryGateway {

    private final DataRemovalInquiryRepository repository;

    @Override
    public void write(DataRemoval dataRemoval, Customer customer) {
        var dto = new DataRemovalInquiryDTO()
                .setRemovalRequested(dataRemoval.requested())
                .setDataRemovalId(dataRemoval.uuid().toString())
                .setCustomerId(customer.uuid().toString())
                .setName(customer.name())
                .setEmail(customer.toEmail())
                .setDocument(customer.toDocument());

        repository.write(dto);
    }
}

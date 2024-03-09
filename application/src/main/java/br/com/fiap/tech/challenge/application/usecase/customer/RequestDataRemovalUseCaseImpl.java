package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.RequestDataRemovalDTO;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalInquiryGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.DataRemovalWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemovalItem;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

import static br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus.PENDING;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CONSUMER_MAY_NOT_BY_REMOVED;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_MAY_NOT_BY_REMOVED;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_REMOVAL_ALREADY_DONE;
import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
class RequestDataRemovalUseCaseImpl implements RequestDataRemovalUseCase {

    private final CustomerReaderGateway customerReaderGateway;
    private final DataRemovalReaderGateway removalReaderGateway;
    private final DataRemovalWriterGateway removalWriterGateway;
    private final DataRemovalInquiryGateway inquiryGateway;

    @Override
    public DataRemoval create(RequestDataRemovalDTO dto) {
        var document = Document.of(dto.getDocument());

        var customer = customerReaderGateway.readByDocument(document)
                .orElseThrow(() -> new ApplicationException(CUSTOMER_NOT_FOUND));

        checkIfRemovalWasRequested(customer);
        checkCustomerRemoval(customer, dto);

        var removal = removalWriterGateway.write(build(customer));

        inquiryGateway.write(removal, customer);

        return removal;
    }

    private void checkIfRemovalWasRequested(Customer customer) {
        var opt = removalReaderGateway.readByCustomerId(customer.uuid());

        if (opt.isPresent()) {
            throw new ApplicationException(CUSTOMER_REMOVAL_ALREADY_DONE);
        }
    }

    private void checkCustomerRemoval(Customer customer, RequestDataRemovalDTO dto) {
        if (customer.document().document().equals("00000000000")) {
            throw new ApplicationException(CONSUMER_MAY_NOT_BY_REMOVED);
        }

        if (!customer.toEmail().equals(dto.getEmail()) || !customer.name().equals(dto.getName())) {
            throw new ApplicationException(CUSTOMER_MAY_NOT_BY_REMOVED);
        }
    }

    private DataRemoval build(Customer customer) {
        var items = new ArrayList<DataRemovalItem>();

        items.add(DataRemovalItem.builder().requested(now()).status(PENDING).application("customer-service").build());
        items.add(DataRemovalItem.builder().requested(now()).status(PENDING).application("purchase-service").build());

        return DataRemoval.builder()
                .requested(now())
                .customerId(customer.uuid())
                .items(items)
                .status(PENDING)
                .build();
    }
}

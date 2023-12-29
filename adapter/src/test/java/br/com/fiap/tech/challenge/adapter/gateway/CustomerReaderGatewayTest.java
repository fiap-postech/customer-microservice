package br.com.fiap.tech.challenge.adapter.gateway;

import br.com.fiap.tech.challenge.adapter.dto.CustomerDTO;
import br.com.fiap.tech.challenge.adapter.gateway.customer.CustomerGatewayFactory;
import br.com.fiap.tech.challenge.adapter.repository.CustomerReaderRepository;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.enabledCustomerModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerReaderGatewayTest {

    @Mock
    private CustomerReaderRepository repository;

    private CustomerReaderGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = CustomerGatewayFactory.customerReaderGateway(repository);
    }

    @Nested
    class FindCustomerByUUID {
        @Test
        void shouldReturnCustomerWhenExists() {
            var customerDTO = create(enabledCustomerDTOModel());
            var customer = create(enabledCustomerModel());
            var uuid = customer.uuid();

            when(repository.readById(uuid.toString())).thenReturn(customerDTO);

            var response = gateway.readById(uuid);

            assertThat(response)
                    .isNotNull()
                    .isEqualTo(customer);

            verify(repository).readById(uuid.toString());
        }

        @Test
        void shouldThrowExceptionWhenCustomerDoesNotExists() {
            var uuid = UUID.randomUUID();
            var uuidText = uuid.toString();
            var exception = new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, uuidText);

            when(repository.readById(uuid.toString())).thenThrow(exception);

            assertThatThrownBy(() -> gateway.readById(uuid))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(repository).readById(uuid.toString());
        }
    }

    @Nested
    class FindCustomerByDocument {

        @Test
        void shouldReturnCustomerWhenExists() {
            var customerDTO = create(enabledCustomerDTOModel());
            var customer = create(enabledCustomerModel());
            var document = customer.document();

            when(repository.readByDocument(document.document())).thenReturn(Optional.of(customerDTO));

            var response = gateway.readByDocument(document);

            assertThat(response)
                    .isNotNull()
                    .isPresent()
                    .contains(customer);

            verify(repository).readByDocument(document.document());
        }

        @Test
        void shouldReturnEmptyWhenCustomerDoesNotExists() {
            var document = Document.of("48826325197");

            when(repository.readByDocument(document.document())).thenReturn(Optional.empty());

            var response = gateway.readByDocument(document);

            assertThat(response)
                    .isNotNull()
                    .isNotPresent();

            verify(repository).readByDocument(document.document());
        }
    }
}

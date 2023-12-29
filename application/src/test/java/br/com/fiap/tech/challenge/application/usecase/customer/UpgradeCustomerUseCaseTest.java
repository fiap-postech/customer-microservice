package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static br.com.fiap.tech.challenge.application.fixture.CreateCustomerDTOFixture.createCustomerDTOInvalidDocumentModel;
import static br.com.fiap.tech.challenge.application.fixture.CreateCustomerDTOFixture.createCustomerDTOModel;
import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.disabledCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.enabledCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpgradeCustomerUseCaseTest {

    @Mock
    private CustomerWriterGateway writerGateway;

    @Mock
    private CustomerReaderGateway readerGateway;

    private UpgradeCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = CustomerUseCaseFactory.upgradeCustomerService(writerGateway, readerGateway);
    }

    @Test
    void shouldDisableCustomer() {
        var customer = create(enabledCustomerModel());
        var document = customer.document();

        when(readerGateway.readByDocument(document)).thenReturn(Optional.of(customer));

        when(writerGateway.write(any(Customer.class)))
                .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

        var response = useCase.disable(document.document());

        assertThat(response).isNotNull().isPresent().contains(create(disabledCustomerModel()));

        verify(readerGateway).readByDocument(document);
        verify(writerGateway).write(any(Customer.class));
    }

    @Test
    void shouldThrowAnErrorWhenDocumentIsInvalid() {
        var document = create(createCustomerDTOInvalidDocumentModel()).getDocument();

        assertThatThrownBy(() -> useCase.disable(document))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("document: The provided document is invalid");

        verify(readerGateway, never()).readByDocument(any(Document.class));
        verify(writerGateway, never()).write(any(Customer.class));
    }

    @Test
    void shouldReturnEmptyWhenCustomerDoesNotExists() {
        var document = create(createCustomerDTOModel()).getDocument();

        when(readerGateway.readByDocument(any(Document.class))).thenReturn(Optional.empty());

        var response = useCase.disable(document);

        assertThat(response)
                .isNotNull()
                .isNotPresent();

        verify(readerGateway).readByDocument(any(Document.class));
        verify(writerGateway, never()).write(any(Customer.class));
    }
}

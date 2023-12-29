package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.fixture.Fixture;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.enabledCustomerModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCustomerByDocumentUseCaseTest {

    @Mock
    private CustomerReaderGateway readerGateway;

    private FindCustomerByDocumentUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = CustomerUseCaseFactory.findCustomerByDocumentService(readerGateway);
    }

    @Test
    void shouldReturnWhenCustomerExists() {
        var customer = Fixture.create(enabledCustomerModel());
        var document = customer.document();

        when(readerGateway.readByDocument(document)).thenReturn(Optional.of(customer));

        var response = useCase.get(document.document());

        assertThat(response)
                .isNotNull()
                .isPresent()
                .contains(customer);

        verify(readerGateway).readByDocument(document);
    }

    @Test
    void shouldThrowExceptionIfDocumentIsInvalid() {
        var document = "12345678908";

        assertThatThrownBy(() -> useCase.get(document))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("document: The provided document is invalid");

        verify(readerGateway, never()).readByDocument(any(Document.class));
    }

    @Test
    void shouldReturnEmptyWhenCustomerDoesNotExists() {
        var document = Document.of("48826325197");

        when(readerGateway.readByDocument(document)).thenReturn(Optional.empty());

        var response = useCase.get(document.document());

        assertThat(response)
                .isNotNull()
                .isNotPresent();

        verify(readerGateway).readByDocument(document);
    }
}

package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.fixture.Fixture;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static br.com.fiap.tech.challenge.application.fixture.CreateCustomerDTOFixture.createCustomerDTOInvalidDocumentModel;
import static br.com.fiap.tech.challenge.application.fixture.CreateCustomerDTOFixture.createCustomerDTOInvalidEmailModel;
import static br.com.fiap.tech.challenge.application.fixture.CreateCustomerDTOFixture.createCustomerDTOModel;
import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.enabledCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_HAS_REGISTRATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @Mock
    private CustomerWriterGateway writerGateway;

    @Mock
    private CustomerReaderGateway readerGateway;

    private CreateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = CustomerUseCaseFactory.createCustomerService(writerGateway, readerGateway);
    }

    @Nested
    class SuccessCases {
        @Test
        void shouldCreateCustomer() {
            var createCustomerDTO = create(createCustomerDTOModel());
            var document = Document.of(createCustomerDTO.getDocument());

            when(readerGateway.readByDocument(document)).thenReturn(Optional.empty());

            when(writerGateway.write(any(Customer.class)))
                    .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

            var response = useCase.create(createCustomerDTO);

            assertThat(response).isNotNull();
            assertThat(response.enabled()).isTrue();
            assertThat(response.name()).isEqualTo(createCustomerDTO.getName());
            assertThat(response.email().email()).isEqualTo(createCustomerDTO.getEmail());
            assertThat(response.document().document()).isEqualTo(createCustomerDTO.getDocument());
            assertThat(response.uuid()).isNotNull();

            verify(readerGateway).readByDocument(document);
            verify(writerGateway).write(any(Customer.class));
        }
    }

    @Nested
    class FailCases {

        @Test
        void shouldThrowAnErrorWhenDocumentIsInvalid() {
            var dto = create(createCustomerDTOInvalidDocumentModel());

            assertThatThrownBy(() -> useCase.create(dto))
                    .isInstanceOf(ConstraintViolationException.class)
                    .hasMessage("document: The provided document is invalid");

            verify(readerGateway, never()).readByDocument(any(Document.class));
            verify(writerGateway, never()).write(any(Customer.class));
        }

        @Test
        void shouldThrowAnErrorWhenEmailIsInvalid() {
            var dto = create(createCustomerDTOInvalidEmailModel());
            var document = Document.of(dto.getDocument());

            when(readerGateway.readByDocument(document)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> useCase.create(dto))
                    .isInstanceOf(ConstraintViolationException.class)
                    .hasMessage("email: must be a well-formed email address");

            verify(readerGateway).readByDocument(document);
            verify(writerGateway, never()).write(any(Customer.class));
        }

        @Test
        void shouldThrowAnErrorWhenDocumentIsUsedForExistingCustomer() {
            var dto = create(createCustomerDTOModel());
            var document = Document.of(dto.getDocument());
            var customer = create(enabledCustomerModel());
            var exception = new ApplicationException(CUSTOMER_HAS_REGISTRATION);

            when(readerGateway.readByDocument(document)).thenReturn(Optional.of(customer));

            assertThatThrownBy(() -> useCase.create(dto))
                    .isInstanceOf(ApplicationException.class)
                    .hasMessage(exception.getMessage());

            verify(readerGateway).readByDocument(document);
            verify(writerGateway, never()).write(any(Customer.class));
        }
    }
}

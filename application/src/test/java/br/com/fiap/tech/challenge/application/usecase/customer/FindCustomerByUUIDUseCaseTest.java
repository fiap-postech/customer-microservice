package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.fixture.Fixture;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static br.com.fiap.tech.challenge.application.fixture.CustomerFixture.enabledCustomerModel;
import static br.com.fiap.tech.challenge.application.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCustomerByUUIDUseCaseTest {

    @Mock
    private CustomerReaderGateway readerGateway;

    private FindCustomerByUUIDUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = CustomerUseCaseFactory.findFindCustomerByUUIDService(readerGateway);
    }

    @Test
    void shouldReturnCustomerWhenExists() {
        var customer = create(enabledCustomerModel());
        var uuid = customer.uuid();

        when(readerGateway.readById(uuid)).thenReturn(customer);

        var response = useCase.get(uuid);

        assertThat(response)
                .isNotNull()
                .isEqualTo(customer);

        verify(readerGateway).readById(uuid);
    }

    @Test
    void shouldThrowExceptionIfCustomerDoesNotExists() {
        var uuid = UUID.randomUUID();
        var exception = new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, uuid.toString());

        when(readerGateway.readById(uuid)).thenThrow(exception);

        assertThatThrownBy(() -> useCase.get(uuid))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(exception.getMessage());

        verify(readerGateway).readById(uuid);
    }
}

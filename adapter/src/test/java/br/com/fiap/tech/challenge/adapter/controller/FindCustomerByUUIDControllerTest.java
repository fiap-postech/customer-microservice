package br.com.fiap.tech.challenge.adapter.controller;

import br.com.fiap.tech.challenge.adapter.controller.customer.CustomerControllerFactory;
import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByUUIDController;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static br.com.fiap.tech.challenge.adapter.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.enabledCustomerModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.CUSTOMER_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCustomerByUUIDControllerTest {

    @Mock
    private FindCustomerByUUIDUseCase useCase;

    @Mock
    private CustomerPresenter presenter;

    private FindCustomerByUUIDController controller;

    @BeforeEach
    void setUp() {
        controller = CustomerControllerFactory.findCustomerByUUIDController(useCase, presenter);
    }

    @Test
    void shouldReturnCustomerWhenExists() {
        var customer = create(enabledCustomerModel());
        var customerDTO = create(enabledCustomerDTOModel());
        var uuid = customer.uuid();

        when(useCase.get(uuid)).thenReturn(customer);
        when(presenter.present(customer)).thenReturn(customerDTO);

        var response = controller.get(uuid.toString());

        assertThat(response)
                .isNotNull()
                .isEqualTo(customerDTO);

        verify(useCase).get(uuid);
        verify(presenter).present(customer);
    }

    @Test
    void shouldThrowExceptionWhenNotExists() {
        var uuid = UUID.randomUUID();

        var exception = new ApplicationException(CUSTOMER_NOT_FOUND_BY_UUID, uuid.toString());

        when(useCase.get(uuid)).thenThrow(exception);

        var UUIDText = uuid.toString();

        assertThatThrownBy(() -> controller.get(UUIDText))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(exception.getMessage());

        verify(useCase).get(uuid);
        verify(presenter, never()).present(any(Customer.class));
    }
}

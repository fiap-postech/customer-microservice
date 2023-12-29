package br.com.fiap.tech.challenge.adapter.controller;

import br.com.fiap.tech.challenge.adapter.controller.customer.CreateCustomerController;
import br.com.fiap.tech.challenge.adapter.controller.customer.CustomerControllerFactory;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.CreateCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.adapter.fixture.CreateCustomerDTOFixture.createCustomerDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.enabledCustomerModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerControllerTest {

    @Mock
    private CreateCustomerUseCase useCase;

    @Mock
    private CustomerPresenter presenter;

    private CreateCustomerController controller;

    @BeforeEach
    void setUp(){
        controller = CustomerControllerFactory.createCustomerController(useCase, presenter);
    }

    @Test
    void shouldCreateCustomer() {
        var createCustomerDTO = create(createCustomerDTOModel());
        var customer = create(enabledCustomerModel());
        var customerDTO = create(enabledCustomerDTOModel());

        when(useCase.create(createCustomerDTO)).thenReturn(customer);
        when(presenter.present(customer)).thenReturn(customerDTO);

        var response = controller.create(createCustomerDTO);

        assertThat(response)
                .isNotNull()
                .isEqualTo(customerDTO);

        verify(useCase).create(createCustomerDTO);
        verify(presenter).present(customer);
    }


}

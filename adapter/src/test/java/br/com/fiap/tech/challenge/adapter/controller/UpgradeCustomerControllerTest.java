package br.com.fiap.tech.challenge.adapter.controller;

import br.com.fiap.tech.challenge.adapter.controller.customer.CustomerControllerFactory;
import br.com.fiap.tech.challenge.adapter.controller.customer.UpgradeCustomerController;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.application.usecase.customer.UpgradeCustomerUseCase;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.fiap.tech.challenge.adapter.fixture.CustomerDTOFixture.disabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.disabledCustomerModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpgradeCustomerControllerTest {

    @Mock
    private UpgradeCustomerUseCase useCase;

    @Mock
    private CustomerPresenter presenter;

    private UpgradeCustomerController controller;

    @BeforeEach
    void setUp() {
        controller = CustomerControllerFactory.upgradeCustomerController(useCase, presenter);
    }

    @Test
    void shouldDisableCustomer() {
        var customer = create(disabledCustomerModel());
        var document = "48826325197";
        var customerDTO = create(disabledCustomerDTOModel());

        when(useCase.disable(document)).thenReturn(Optional.of(customer));
        when(presenter.present(customer)).thenReturn(customerDTO);

        var response = controller.disable(document);

        assertThat(response)
                .isNotNull()
                .isPresent()
                .contains(customerDTO);

        verify(useCase).disable(document);
        verify(presenter).present(customer);
    }

    @Test
    void shouldReturnEmptyWhenAskToDisableCustomerThatNoExists() {
        var document = "48826325197";

        when(useCase.disable(document)).thenReturn(Optional.empty());

        var response = controller.disable(document);

        assertThat(response)
                .isNotNull()
                .isNotPresent();

        verify(useCase).disable(document);
        verify(presenter, never()).present(any(Customer.class));
    }
}

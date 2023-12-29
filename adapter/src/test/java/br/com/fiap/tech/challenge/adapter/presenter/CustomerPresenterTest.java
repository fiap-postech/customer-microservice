package br.com.fiap.tech.challenge.adapter.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.fiap.tech.challenge.adapter.fixture.CustomerFixture.enabledCustomerModel;
import static br.com.fiap.tech.challenge.adapter.fixture.Fixture.create;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerPresenterTest {

    private CustomerPresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = PresenterFactory.customerPresenter();
    }

    @Test
    void shouldPresentCustomer() {
        var customer = create(enabledCustomerModel());

        var response = presenter.present(customer);

        assertThat(response.getId()).isEqualTo(customer.uuid().toString());
        assertThat(response.getName()).isEqualTo(customer.name());
        assertThat(response.getEmail()).isEqualTo(customer.email().email());
        assertThat(response.getDocument()).isEqualTo(customer.document().document());
    }
}

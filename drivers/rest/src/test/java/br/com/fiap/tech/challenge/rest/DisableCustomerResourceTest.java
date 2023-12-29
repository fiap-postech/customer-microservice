package br.com.fiap.tech.challenge.rest;

import br.com.fiap.tech.challenge.adapter.controller.customer.UpgradeCustomerController;
import br.com.fiap.tech.challenge.rest.mapping.CustomerResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.DisableCustomerResource;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.fiap.tech.challenge.fixture.CustomerDTOFixture.disabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.fixture.Fixture.create;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DisableCustomerResourceTest extends ResourceBaseTestSuite<DisableCustomerResource> {

    @Mock
    private UpgradeCustomerController upgradeCustomerController;

    @Test
    void shouldDisableWhenCustomerExists() throws Exception {
        var customerDTO = create(disabledCustomerDTOModel());
        var document = customerDTO.getDocument();

        when(upgradeCustomerController.disable(document)).thenReturn(Optional.of(customerDTO));

        mockMvc.perform(patch("/customer/{document}/disable", document))
                .andExpect(status().isOk());

        verify(upgradeCustomerController).disable(document);
    }

    @Test
    void shouldReturnBadRequestWhenDocumentIsInvalid() throws Exception {
        var document = "12345678908";

        mockMvc.perform(patch("/customer/{document}/disable", document))
                .andExpect(status().isBadRequest());

        verify(upgradeCustomerController, never()).disable(document);
    }

    @Test
    void shouldReturnNotFoundWhenCustomerDoesNotExists() throws Exception {
        var customerDTO = create(disabledCustomerDTOModel());
        var document = customerDTO.getDocument();

        when(upgradeCustomerController.disable(document)).thenReturn(Optional.empty());

        mockMvc.perform(patch("/customer/{document}/disable", document))
                .andExpect(status().isNoContent());

        verify(upgradeCustomerController).disable(document);
    }

    @Override
    DisableCustomerResource createController() {
        return new DisableCustomerResource(
                CustomerResponseMapper.INSTANCE,
                upgradeCustomerController
        );
    }
}

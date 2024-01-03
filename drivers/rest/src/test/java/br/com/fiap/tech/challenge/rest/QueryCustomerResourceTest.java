package br.com.fiap.tech.challenge.rest;

import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByDocumentController;
import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByUUIDController;
import br.com.fiap.tech.challenge.rest.mapping.CustomerResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.QueryCustomerResource;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.fiap.tech.challenge.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.fixture.Fixture.create;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class QueryCustomerResourceTest extends ResourceBaseTestSuite<QueryCustomerResource> {

    @Mock
    private FindCustomerByDocumentController findCustomerByDocumentController;

    @Mock
    private FindCustomerByUUIDController findCustomerByUUIDController;

    @Test
    void shouldQuerySuccessFullyWhenCustomerExists() throws Exception {
        var customerDTO = create(enabledCustomerDTOModel());
        var document = customerDTO.getDocument();

        when(findCustomerByDocumentController.get(document)).thenReturn(Optional.of(customerDTO));

        var requestBuilder = get("/customer")
                .param("document", document);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(findCustomerByDocumentController).get(document);
    }

    @Test
    void shouldReturnBadRequestWhenDocumentIsInvalid() throws Exception {
        var document = "12345678908";

        var requestBuilder = get("/customer")
                .param("document", document);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());

        verify(findCustomerByDocumentController, never()).get(document);
    }

    @Test
    void shouldReturnNotFoundWhenCustomerDoesNotExists() throws Exception {
        var document = "12345678909";

        when(findCustomerByDocumentController.get(document)).thenReturn(Optional.empty());

        var requestBuilder = get("/customer")
                .param("document", document);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());

        verify(findCustomerByDocumentController).get(document);
    }

    @Override
    QueryCustomerResource createController() {
        return new QueryCustomerResource(
                CustomerResponseMapper.INSTANCE,
                findCustomerByDocumentController,
                findCustomerByUUIDController
        );
    }
}

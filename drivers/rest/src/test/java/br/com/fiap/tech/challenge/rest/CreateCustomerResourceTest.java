package br.com.fiap.tech.challenge.rest;

import br.com.fiap.tech.challenge.adapter.controller.customer.CreateCustomerController;
import br.com.fiap.tech.challenge.application.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.rest.mapping.CreateCustomerMapper;
import br.com.fiap.tech.challenge.rest.mapping.CustomerResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.CreateCustomerResource;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static br.com.fiap.tech.challenge.fixture.CreateCustomerRequestFixture.createCustomerRequestModel;
import static br.com.fiap.tech.challenge.fixture.CustomerDTOFixture.enabledCustomerDTOModel;
import static br.com.fiap.tech.challenge.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.util.JsonUtil.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CreateCustomerResourceTest extends ResourceBaseTestSuite<CreateCustomerResource> {

    @Mock
    private CreateCustomerController createCustomerController;

    @Test
    void shouldCreateCustomer() throws Exception {
        var customerDTO = create(enabledCustomerDTOModel());
        var request = create(createCustomerRequestModel());

        when(createCustomerController.create(any(CreateCustomerDTO.class))).thenReturn(customerDTO);

        var requestBuilder = post("/customer")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(request));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        verify(createCustomerController).create(any(CreateCustomerDTO.class));
    }

    @Override
    CreateCustomerResource createController() {
        return new CreateCustomerResource(
                CreateCustomerMapper.INSTANCE,
                CustomerResponseMapper.INSTANCE,
                createCustomerController
        );
    }
}

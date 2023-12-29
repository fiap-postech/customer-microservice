package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.customer.CreateCustomerController;
import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByDocumentController;
import br.com.fiap.tech.challenge.adapter.controller.customer.UpgradeCustomerController;
import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.rest.mapping.CreateCustomerMapper;
import br.com.fiap.tech.challenge.rest.mapping.CustomerResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.CustomerResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerResource implements CustomerResourceDoc {

    private final CreateCustomerMapper createCustomerMapper;
    private final CustomerResponseMapper responseMapper;

    private final CreateCustomerController createCustomerController;
    private final FindCustomerByDocumentController findCustomerByDocumentController;
    private final UpgradeCustomerController upgradeCustomerController;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@RequestBody @Valid CreateCustomerRequest request) {
        return responseMapper.toResponse(
                createCustomerController.create(createCustomerMapper.toDTO(request))
        );
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getByDocument(@RequestParam("document") @DocumentCustomer String document) {
        return findCustomerByDocumentController.get(document)
                .map(customer -> ResponseEntity.ok(responseMapper.toResponse(customer)))
                .orElse(ResponseEntity.noContent().build());
    }

    @PatchMapping("/{document}/disable")
    public ResponseEntity<CustomerResponse> disable(@PathVariable("document") @DocumentCustomer String document) {
        return upgradeCustomerController.disable(document)
                .map(customer -> ResponseEntity.ok(responseMapper.toResponse(customer)))
                .orElse(ResponseEntity.noContent().build());
    }
}

package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByDocumentController;
import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.rest.mapping.CustomerResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.QueryCustomerResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class QueryCustomerResource implements QueryCustomerResourceDoc {

    private final CustomerResponseMapper responseMapper;

    private final FindCustomerByDocumentController findCustomerByDocumentController;


    @GetMapping
    public ResponseEntity<CustomerResponse> getByDocument(@RequestParam("document") @DocumentCustomer String document) {
        return findCustomerByDocumentController.get(document)
                .map(customer -> ResponseEntity.ok(responseMapper.toResponse(customer)))
                .orElse(ResponseEntity.noContent().build());
    }

}

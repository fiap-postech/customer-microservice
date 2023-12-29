package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.customer.UpgradeCustomerController;
import br.com.fiap.tech.challenge.rest.mapping.CustomerResponseMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.DisableCustomerResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class DisableCustomerResource implements DisableCustomerResourceDoc {

    private final CustomerResponseMapper responseMapper;

    private final UpgradeCustomerController upgradeCustomerController;


    @PatchMapping("/{document}/disable")
    public ResponseEntity<CustomerResponse> disable(@PathVariable("document") String document) {
        return upgradeCustomerController.disable(document)
                .map(customer -> ResponseEntity.ok(responseMapper.toResponse(customer)))
                .orElse(ResponseEntity.noContent().build());
    }
}

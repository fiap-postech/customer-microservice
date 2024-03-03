package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.adapter.controller.customer.FindDataRemovalByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.customer.RequestDataRemovalController;
import br.com.fiap.tech.challenge.rest.mapping.DataRemovalResponseMapper;
import br.com.fiap.tech.challenge.rest.mapping.RequestDataRemovalMapper;
import br.com.fiap.tech.challenge.rest.resource.doc.DataRemovalResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.DataRemovalRequest;
import br.com.fiap.tech.challenge.rest.resource.response.DataRemovalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/data/removal")
@RequiredArgsConstructor
public class DataRemovalResource implements DataRemovalResourceDoc {

    private final RequestDataRemovalController requestController;
    private final FindDataRemovalByUUIDController findController;
    private final RequestDataRemovalMapper requestMapper;
    private final DataRemovalResponseMapper responseMapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DataRemovalResponse create(@Valid @RequestBody DataRemovalRequest request) {
        var dto = requestController.create(requestMapper.toDTO(request));
        return responseMapper.toResponse(dto);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DataRemovalResponse> getById(@PathVariable("id") String id) {
        return findController.get(id)
                .map(responseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}

package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Cliente")
public class CreateCustomerRequest extends Request<Customer> {

    @Serial
    private static final long serialVersionUID = 8954437605317971024L;

    @NotBlank
    @Schema(description = "Nome Completo do Cliente", example = "Luke Skywalker")
    private String name;

    @NotBlank
    @Email
    @Schema(description = "Email do Cliente", example = "luke_skywalker@jediorder.com")
    private String email;

    @DocumentCustomer
    @Schema(description = "Documento do Cliente", example = "02903784000")
    private String document;

}

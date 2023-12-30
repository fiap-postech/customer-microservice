package br.com.fiap.tech.challenge.rest.resource.doc;

import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Cliente", description = "API responsável pelo gerenciamento de Cliente")
public interface CreateCustomerResourceDoc {

     @Operation(
            summary = "Cadastra um novo cliente",
            description = "Cadastra um novo cliente no banco de dados.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Retorno em caso de sucesso em que o cliente foi cadastrado", content = { @Content(schema = @Schema(implementation = CustomerResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do cliente está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "409", description = "Retorno em caso de tentar criar cliente com CPF que já foi usado por outro cliente", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CustomerResponse create(CreateCustomerRequest request);

}

package br.com.fiap.tech.challenge.rest.resource.doc;

import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cliente", description = "API responsável pelo gerenciamento de Cliente")
public interface QueryCustomerResourceDoc {

    @Operation(
            summary = "Retorna um cliente pelo Documento",
            description = "Busca um cliente cadastrado no banco de dados pelo Documento informado",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Retorno dos dados do cliente em caso de sucesso de acordo com a requisição pedida", useReturnTypeSchema = true),
                @ApiResponse(responseCode = "204", description = "O cliente para o documento fornecido não foi encontrado", content = { @Content(schema = @Schema()) }),
                @ApiResponse(responseCode = "400", description = "Retorno em caso que o documento informado é inválido", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    ResponseEntity<CustomerResponse> getByDocument(@DocumentCustomer  @Parameter(description = "Documento para pesquisa de um cliente", required = true) String document);

}

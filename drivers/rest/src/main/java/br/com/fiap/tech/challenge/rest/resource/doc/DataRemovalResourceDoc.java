package br.com.fiap.tech.challenge.rest.resource.doc;

import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.resource.request.DataRemovalRequest;
import br.com.fiap.tech.challenge.rest.resource.response.DataRemovalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cliente", description = "API responsável pela remoção dos dos dados do Cliente")
public interface DataRemovalResourceDoc {

    @Operation(
            summary = "Cria uma solicitação de Remoção de Dados do Cliente",
            description = "Cria uma solicitação de Remoção de Dados do Cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Retorno em caso de sucesso no cadastro da solicitação", content = { @Content(schema = @Schema(implementation = DataRemovalResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do cliente está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "409", description = "Retorno em caso de haver solicitação em duplicidade", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    DataRemovalResponse create(DataRemovalRequest request);

    @Operation(
            summary = "Consulta uma solicitação de Remoção de Dados do Cliente",
            description = "Consulta uma solicitação de Remoção de Dados do Cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retorno em caso de sucesso na consulta", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "Retorno em caso de não encontrar a solicitação requisitada", content = { @Content(schema = @Schema()) }),
            }
    )
    ResponseEntity<DataRemovalResponse> getById(@Parameter(in = ParameterIn.PATH, description = "Id da Solicitação", required = true) String id);

}

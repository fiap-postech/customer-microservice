package br.com.fiap.tech.challenge.rest.resource.response;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de uma Solicitação de Remoção de Dados de Cliente")
public class DataRemovalResponse extends Response {
    @Serial
    private static final long serialVersionUID = 474755157067690662L;

    @Schema(
            description = "Identificador da Solicitação de Remoção de Dados",
            example = "ee43a548-8f29-4557-ad2d-a130bd3beada"
    )
    private String id;

    @Schema(
            description = "Identificador",
            example = "62e5f9e8-d8be-482f-9dad-f3f5260d2ef4"
    )
    private String customerId;

    @Schema(
            description = "Status da Solicitação",
            example = "PENDING"
    )
    private DataRemovalStatus status;

    @Schema(
            description = "Indica quando o cliente solicitou a remoção dos seus dados",
            example = "2024-02-26T23:59:60Z"
    )
    private LocalDateTime requested;
}

package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;
import br.com.fiap.tech.challenge.enterprise.validation.DocumentCustomer;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de uma Solicitação de Remoção de Dados de Cliente")
public class DataRemovalRequest extends Request<DataRemoval> {
    @Serial
    private static final long serialVersionUID = -6107943516235958660L;

    @DocumentCustomer
    @Schema(description = "Documento do Cliente", example = "02903784000")
    private String document;
}

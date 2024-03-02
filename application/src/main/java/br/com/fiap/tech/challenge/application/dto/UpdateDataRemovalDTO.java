package br.com.fiap.tech.challenge.application.dto;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors
public class UpdateDataRemovalDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 393294928879454036L;

    private String id;
    private String application;
    private DataRemovalStatus status;
}

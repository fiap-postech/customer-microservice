package br.com.fiap.tech.challenge.application.dto;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors
public class DataRemovalDoneDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6734072752820724699L;

    private String id;
    private String application;
    private DataRemovalStatus status;

}

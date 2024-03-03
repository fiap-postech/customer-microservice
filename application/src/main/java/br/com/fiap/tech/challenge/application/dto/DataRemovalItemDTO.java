package br.com.fiap.tech.challenge.application.dto;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DataRemovalItemDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1344171704339193682L;

    private String id;
    private String application;
    private DataRemovalStatus status;
    private LocalDateTime requested;

}

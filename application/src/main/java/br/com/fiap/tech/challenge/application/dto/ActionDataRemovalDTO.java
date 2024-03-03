package br.com.fiap.tech.challenge.application.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ActionDataRemovalDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8677798649557560227L;

    private String id;
    private String document;
}

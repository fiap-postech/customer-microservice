package br.com.fiap.tech.challenge.application.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RequestDataRemovalDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5006551194085986509L;

    private String name;
    private String email;
    private String document;
}

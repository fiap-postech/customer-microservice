package br.com.fiap.tech.challenge.adapter.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RequestDataRemovalDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8677798649557560227L;

    private String id;
    private String document;
}

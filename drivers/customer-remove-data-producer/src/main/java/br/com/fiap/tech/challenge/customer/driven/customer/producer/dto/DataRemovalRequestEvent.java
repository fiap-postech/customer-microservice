package br.com.fiap.tech.challenge.customer.driven.customer.producer.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DataRemovalRequestEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = 3567779237866679215L;

    private String id;
    private String document;
}

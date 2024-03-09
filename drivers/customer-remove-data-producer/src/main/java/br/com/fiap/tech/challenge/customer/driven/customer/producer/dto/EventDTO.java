package br.com.fiap.tech.challenge.customer.driven.customer.producer.dto;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventDTO {

    private String id;
    private String application;
    private DataRemovalStatus status;

}

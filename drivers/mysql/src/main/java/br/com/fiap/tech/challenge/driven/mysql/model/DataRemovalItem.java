package br.com.fiap.tech.challenge.driven.mysql.model;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Embeddable
public class DataRemovalItem implements Serializable {

    @Serial
    private static final long serialVersionUID = -7242035186881411087L;

    @NotBlank
    private String uuid;

    @NotBlank
    private String application;

    @Enumerated(EnumType.STRING)
    private DataRemovalStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime requested;
}

package br.com.fiap.tech.challenge.driven.mysql.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Getter
@Setter
@ToString
@MappedSuperclass
public class UUIDEntity extends AuditedEntity{
    @Serial
    private static final long serialVersionUID = 7652278998447843504L;

    @Column(name = "uuid", unique = true)
    private String uuid;
}

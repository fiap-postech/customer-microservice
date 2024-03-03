package br.com.fiap.tech.challenge.driven.mysql.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
public class CustomerEntity extends JPAEntity{
    @Serial
    private static final long serialVersionUID = -6898703406360602814L;

    @NotBlank
    private String name;

    @NotBlank
    @Column(columnDefinition = "text")
    private String email;

    @Column
    private String document;

    private boolean enabled;
}

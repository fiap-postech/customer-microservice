package br.com.fiap.tech.challenge.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.enterprise.valueobject.EmailRegistration;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serial;
import java.util.UUID;

import static java.util.Objects.isNull;

@Getter
@Accessors(fluent = true)
public class Customer extends Entity {

    @Serial
    private static final long serialVersionUID = 213659655671060163L;

    @NotBlank
    private final String name;

    private final EmailRegistration email;

    private final Document document;

    private final boolean enabled;

    @Builder(toBuilder = true)
    public Customer(@Builder.ObtainVia(method = "uuid") UUID uuid, String name, EmailRegistration email, Document document, boolean enabled) {
        super(uuid);

        this.name = name;
        this.email = email;
        this.document = document;
        this.enabled = enabled;

        validate();
    }

    public Customer disable(){
        return toBuilder()
                .enabled(false)
                .build();
    }

    public String toDocument() {
        return document.document();
    }

    public String toEmail() {
        return isNull(email) ? null : email.email();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder()
                .append(enabled, customer.enabled)
                .append(name, customer.name)
                .append(email, customer.email)
                .append(document, customer.document)
                .appendSuper(true)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(email)
                .append(document)
                .append(enabled)
                .appendSuper(27)
                .toHashCode();
    }
}

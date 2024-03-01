package br.com.fiap.tech.challenge.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class DataRemovalItem extends Entity{

    @Serial
    private static final long serialVersionUID = 191765703736808239L;

    @NotBlank
    private final String application;

    @NotNull
    private final DataRemovalStatus status;

    @NotNull
    private final LocalDateTime requested;

    @Builder(toBuilder = true)
    public DataRemovalItem(@Builder.ObtainVia(method = "uuid") UUID uuid,
                           String application,
                           DataRemovalStatus status,
                           LocalDateTime requested) {
        super(uuid);

        this.application = application;
        this.status = status;
        this.requested = requested;

        validate();
    }

    public DataRemovalItem error() {
        return toBuilder()
                .status(DataRemovalStatus.ERROR)
                .build();
    }

    public DataRemovalItem finish() {
        return toBuilder()
                .status(DataRemovalStatus.FINISHED)
                .build();
    }
}

package br.com.fiap.tech.challenge.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class DataRemoval extends Entity {

    @Serial
    private static final long serialVersionUID = 4242005977920680874L;

    @NotNull
    private final UUID customerId;

    @NotNull
    private final DataRemovalStatus status;

    @NotNull
    private final LocalDateTime requested;

    @NotEmpty
    @NotNull
    @Valid
    private final List<DataRemovalItem> items;

    @Builder(toBuilder = true)
    public DataRemoval(@Builder.ObtainVia(method = "uuid") UUID uuid,
                       UUID customerId,
                       DataRemovalStatus status,
                       LocalDateTime requested,
                       List<DataRemovalItem> items) {
        super(uuid);

        this.customerId = customerId;
        this.status = status;
        this.requested = requested;
        this.items = items;

        validate();
    }

    public DataRemoval finish() {
        return toBuilder()
                .status(DataRemovalStatus.FINISHED)
                .build();
    }
}

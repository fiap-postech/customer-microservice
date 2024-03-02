package br.com.fiap.tech.challenge.driven.mysql.model;

import br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "customer_data_removal")
public class DataRemovalEntity extends JPAEntity{
    @Serial
    private static final long serialVersionUID = 428387819879421173L;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DataRemovalStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime requested;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_data_removal_item", joinColumns = @JoinColumn(name = "removal_id"))
    private List<DataRemovalItem> items;
}

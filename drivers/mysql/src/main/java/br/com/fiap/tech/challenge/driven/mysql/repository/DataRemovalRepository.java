package br.com.fiap.tech.challenge.driven.mysql.repository;

import br.com.fiap.tech.challenge.driven.mysql.model.DataRemovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataRemovalRepository extends JpaRepository<DataRemovalEntity, Long> {

    Optional<DataRemovalEntity> findByUuid(String id);

    Optional<DataRemovalEntity> findByCustomerUuid(String id);

}

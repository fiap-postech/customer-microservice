package br.com.fiap.tech.challenge.driven.mysql.repository;

import br.com.fiap.tech.challenge.driven.mysql.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByUuid(String uuid);

    Optional<CustomerEntity> findByUuid(String uuid);
    Optional<CustomerEntity> findByDocumentAndEnabled(String document, boolean enabled);
}

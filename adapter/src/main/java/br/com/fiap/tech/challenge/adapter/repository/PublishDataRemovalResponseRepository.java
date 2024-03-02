package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.application.dto.DataRemovalItemDTO;

public interface PublishDataRemovalResponseRepository {

    void publish(DataRemovalItemDTO dto);

}

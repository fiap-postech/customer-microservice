package br.com.fiap.tech.challenge.adapter.repository;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;

public interface PublishDataRemovalResponseRepository {

    void publish(DataRemovalDoneDTO dto);

}

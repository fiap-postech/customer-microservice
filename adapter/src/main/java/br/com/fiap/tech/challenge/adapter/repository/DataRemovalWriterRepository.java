package br.com.fiap.tech.challenge.adapter.repository;


import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;

public interface DataRemovalWriterRepository {
    DataRemovalDTO write(DataRemovalDTO data);
}

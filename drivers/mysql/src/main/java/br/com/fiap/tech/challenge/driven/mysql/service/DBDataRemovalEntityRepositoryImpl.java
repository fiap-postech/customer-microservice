package br.com.fiap.tech.challenge.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.repository.DataRemovalReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalWriterRepository;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.driven.mysql.mapping.DBDataRemovalMapper;
import br.com.fiap.tech.challenge.driven.mysql.repository.DataRemovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DBDataRemovalEntityRepositoryImpl implements DataRemovalWriterRepository, DataRemovalReaderRepository {

    private final DBDataRemovalMapper mapper;
    private final DataRemovalRepository repository;

    @Override
    public DataRemovalDTO write(DataRemovalDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Optional<DataRemovalDTO> readById(String id) {
        return repository.findByUuid(id)
                .map(mapper::toDTO);
    }

    @Override
    public Optional<DataRemovalDTO> readByCustomerId(String id) {
        return repository.findByCustomerUuid(id)
                .map(mapper::toDTO);
    }
}

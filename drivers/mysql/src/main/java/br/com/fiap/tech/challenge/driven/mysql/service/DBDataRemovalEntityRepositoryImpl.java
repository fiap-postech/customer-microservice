package br.com.fiap.tech.challenge.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.repository.DataRemovalReaderRepository;
import br.com.fiap.tech.challenge.adapter.repository.DataRemovalWriterRepository;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.DataRemovalItemDTO;
import br.com.fiap.tech.challenge.driven.mysql.mapping.DBDataRemovalMapper;
import br.com.fiap.tech.challenge.driven.mysql.model.DataRemovalEntity;
import br.com.fiap.tech.challenge.driven.mysql.model.DataRemovalItem;
import br.com.fiap.tech.challenge.driven.mysql.repository.DataRemovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.fiap.tech.challenge.enterprise.enums.DataRemovalStatus.FINISHED;

@Service
@RequiredArgsConstructor
public class DBDataRemovalEntityRepositoryImpl implements DataRemovalWriterRepository, DataRemovalReaderRepository {

    private final DBDataRemovalMapper mapper;
    private final DataRemovalRepository repository;

    @Override
    public DataRemovalDTO write(DataRemovalDTO dto) {
        return repository.findByUuid(dto.getId())
                .map(e -> updateDataRemoval(e, dto))
                .orElseGet(() -> createDataRemoval(dto));
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

    private DataRemovalDTO updateDataRemoval(DataRemovalEntity entity, DataRemovalDTO dto) {
        for (var item : dto.getItems()) {
            if (alreadyExists(entity.getItems(), item.getApplication())) {
                updateItem(entity.getItems(), item);
            } else {
                entity.getItems().add(mapper.toEntity(item));
            }
        }

        if (entity.getItems().stream().allMatch(i -> i.getStatus().equals(FINISHED))) {
            entity.setStatus(FINISHED);
        }

        return mapper.toDTO(repository.save(entity));
    }

    private boolean alreadyExists(List<DataRemovalItem> items, String application) {
        return items.stream().anyMatch(i -> i.getApplication().equals(application));
    }

    private void updateItem(List<DataRemovalItem> items, DataRemovalItemDTO dto) {
        for (var item : items) {
            if (item.getApplication().equals(dto.getApplication())){
                item.setStatus(dto.getStatus());
            }
        }
    }

    private DataRemovalDTO createDataRemoval(DataRemovalDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }
}

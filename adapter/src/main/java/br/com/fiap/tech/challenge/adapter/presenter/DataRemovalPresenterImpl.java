package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.adapter.mapping.DataRemovalMapper;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

class DataRemovalPresenterImpl implements DataRemovalPresenter {
    @Override
    public DataRemovalDTO present(DataRemoval data) {
        return DataRemovalMapper.INSTANCE.toDTO(data);
    }
}

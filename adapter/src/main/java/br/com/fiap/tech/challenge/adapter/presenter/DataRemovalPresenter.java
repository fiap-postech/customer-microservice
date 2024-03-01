package br.com.fiap.tech.challenge.adapter.presenter;

import br.com.fiap.tech.challenge.application.dto.DataRemovalDTO;
import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

public interface DataRemovalPresenter {

    DataRemovalDTO present(DataRemoval data);
}

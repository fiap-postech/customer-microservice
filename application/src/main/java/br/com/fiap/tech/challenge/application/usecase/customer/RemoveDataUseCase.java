package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.dto.ActionDataRemovalDTO;
import br.com.fiap.tech.challenge.application.dto.DataRemovalDoneDTO;

public interface RemoveDataUseCase {

    DataRemovalDoneDTO remove(ActionDataRemovalDTO dto);

}

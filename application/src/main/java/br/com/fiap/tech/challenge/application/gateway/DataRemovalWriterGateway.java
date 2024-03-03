package br.com.fiap.tech.challenge.application.gateway;

import br.com.fiap.tech.challenge.enterprise.entity.DataRemoval;

public interface DataRemovalWriterGateway {
    DataRemoval write(DataRemoval data);
}

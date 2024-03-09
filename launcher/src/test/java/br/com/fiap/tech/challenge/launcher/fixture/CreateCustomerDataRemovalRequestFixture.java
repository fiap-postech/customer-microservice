package br.com.fiap.tech.challenge.launcher.fixture;

import br.com.fiap.tech.challenge.rest.resource.request.DataRemovalRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCustomerDataRemovalRequestFixture {

    public static Model<DataRemovalRequest> customerDataRemovalRequestModel() {
        return Instancio.of(DataRemovalRequest.class)
                .set(field(DataRemovalRequest::getDocument), "32495281885")
                .set(field(DataRemovalRequest::getName), "Juan Luan Danilo Fernandes")
                .set(field(DataRemovalRequest::getEmail), "juan.luan.fernandes@click21.com.br")
                .toModel();
    }

    public static Model<DataRemovalRequest> customerDataRemovalInvalidDataNameRequestModel() {
        return Instancio.of(DataRemovalRequest.class)
                .set(field(DataRemovalRequest::getDocument), "32495281885")
                .set(field(DataRemovalRequest::getName), "Juan Manuel Danilo Fernandes")
                .set(field(DataRemovalRequest::getEmail), "juan.luan.fernandes@click21.com.br")
                .toModel();
    }

    public static Model<DataRemovalRequest> customerDataRemovalInvalidDataEmailRequestModel() {
        return Instancio.of(DataRemovalRequest.class)
                .set(field(DataRemovalRequest::getDocument), "32495281885")
                .set(field(DataRemovalRequest::getName), "Juan Luan Danilo Fernandes")
                .set(field(DataRemovalRequest::getEmail), "juan.manuel.fernandes@click21.com.br")
                .toModel();
    }

    public static Model<DataRemovalRequest> consumerCustomerDataRemovalRequestModel() {
        return Instancio.of(DataRemovalRequest.class)
                .set(field(DataRemovalRequest::getDocument), "00000000000")
                .set(field(DataRemovalRequest::getName), "Consumidor")
                .set(field(DataRemovalRequest::getEmail), "consumidor@techchallenge.com")
                .toModel();
    }

    public static Model<DataRemovalRequest> customerWithAnExistantDataRemovalRequestModel() {
        return Instancio.of(DataRemovalRequest.class)
                .set(field(DataRemovalRequest::getDocument), "65881292383")
                .set(field(DataRemovalRequest::getName), "Isabelle Antônia Oliveira")
                .set(field(DataRemovalRequest::getEmail), "isabelle_oliveira@fcacomputers.com.br")
                .toModel();
    }



}

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
                .toModel();
    }

    public static Model<DataRemovalRequest> consumerCustomerDataRemovalRequestModel() {
        return Instancio.of(DataRemovalRequest.class)
                .set(field(DataRemovalRequest::getDocument), "00000000000")
                .toModel();
    }

    public static Model<DataRemovalRequest> customerWithAnExistantDataRemovalRequestModel() {
        return Instancio.of(DataRemovalRequest.class)
                .set(field(DataRemovalRequest::getDocument), "65881292383")
                .toModel();
    }



}

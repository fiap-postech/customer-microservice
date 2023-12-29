package br.com.fiap.tech.challenge.fixture;

import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.instancio.Instancio;
import org.instancio.Model;

import static org.instancio.Select.field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCustomerRequestFixture {

    public static Model<CreateCustomerRequest> createCustomerRequestModel() {
        return Instancio.of(CreateCustomerRequest.class)
                .set(field(CreateCustomerRequest::getName), "Jose da Silva")
                .set(field(CreateCustomerRequest::getDocument), "19748826325")
                .set(field(CreateCustomerRequest::getEmail), "jose.silva@gmail.com")
                .toModel();
    }
}

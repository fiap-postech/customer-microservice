package br.com.fiap.tech.challenge.launcher.fixture;

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

    public static Model<CreateCustomerRequest> createCustomerRequestDocumentAlreadyRegisteredModel() {
        return Instancio.of(createCustomerRequestModel())
                .set(field(CreateCustomerRequest::getDocument), "89093796852")
                .toModel();
    }

    public static Model<CreateCustomerRequest> createCustomerRequestMissingDocumentModel() {
        return Instancio.of(createCustomerRequestModel())
                .set(field(CreateCustomerRequest::getDocument), null)
                .toModel();
    }

    public static Model<CreateCustomerRequest> createCustomerRequestMissingEmailModel() {
        return Instancio.of(createCustomerRequestModel())
                .set(field(CreateCustomerRequest::getEmail), null)
                .toModel();
    }

    public static Model<CreateCustomerRequest> createCustomerRequestMissingNameModel() {
        return Instancio.of(createCustomerRequestModel())
                .set(field(CreateCustomerRequest::getName), null)
                .toModel();
    }

    public static Model<CreateCustomerRequest> createCustomerRequestInvalidDocumentModel() {
        return Instancio.of(createCustomerRequestModel())
                .set(field(CreateCustomerRequest::getDocument), "12345678908")
                .toModel();
    }

    public static Model<CreateCustomerRequest> createCustomerRequestInvalidEmailModel() {
        return Instancio.of(createCustomerRequestModel())
                .set(field(CreateCustomerRequest::getEmail), "jose.silva")
                .toModel();
    }
}

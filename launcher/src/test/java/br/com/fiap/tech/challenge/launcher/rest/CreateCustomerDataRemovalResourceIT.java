package br.com.fiap.tech.challenge.launcher.rest;

import br.com.fiap.tech.challenge.launcher.config.TestConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.DataRemovalRequest;
import org.instancio.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.launcher.container.DatabaseContainers.localDatabaseContainer;
import static br.com.fiap.tech.challenge.launcher.container.LocalStackContainers.localStackContainer;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerDataRemovalRequestFixture.consumerCustomerDataRemovalRequestModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerDataRemovalRequestFixture.customerDataRemovalInvalidDataEmailRequestModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerDataRemovalRequestFixture.customerDataRemovalInvalidDataNameRequestModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerDataRemovalRequestFixture.customerDataRemovalRequestModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerDataRemovalRequestFixture.customerWithAnExistantDataRemovalRequestModel;
import static br.com.fiap.tech.challenge.launcher.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.launcher.util.ConfigurationOverrides.overrideConfiguration;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({ "test" })
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class CreateCustomerDataRemovalResourceIT {

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @Container
    protected static GenericContainer<?> LOCAL_STACK_CONTAINER = localStackContainer();

    @DynamicPropertySource
    static void overrideConfig(DynamicPropertyRegistry registry) {
        overrideConfiguration(registry, DATABASE, LOCAL_STACK_CONTAINER);
    }

    @Test
    void shouldCreateCustomerDataRemoval() {
        var request = create(customerDataRemovalRequestModel());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
            .when()
                .post("/customer/data/removal")
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CustomerDataRemovalSchema.json"));
    }

    @Test
    void shouldGetAnErrorWhenTryToRemoveDataFromConsumer() {
        var request = create(consumerCustomerDataRemovalRequestModel());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/customer/data/removal")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("./schemas/APIErrorSchema.json"));
    }

    @ParameterizedTest
    @MethodSource("invalidDataSource")
    void shouldGetAnErrorWhenTryToRemoveDataFromConsumerWithInvalidRequestData(Model<DataRemovalRequest> model) {
        var request = create(model);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/customer/data/removal")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("./schemas/APIErrorSchema.json"));
    }

    @Test
    void shouldGetAnErrorWhenTryToRemoveDataFromACustomerThatAlreadyHadThisRequestKind() {
        var request = create(customerWithAnExistantDataRemovalRequestModel());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/customer/data/removal")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body(matchesJsonSchemaInClasspath("./schemas/APIErrorSchema.json"));
    }

    static Stream<Model<DataRemovalRequest>> invalidDataSource() {
        return Stream.of(
                customerDataRemovalInvalidDataNameRequestModel(),
                customerDataRemovalInvalidDataEmailRequestModel()
        );
    }
}

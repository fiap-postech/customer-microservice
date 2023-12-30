package br.com.fiap.tech.challenge.launcher.rest;

import br.com.fiap.tech.challenge.launcher.config.TestConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import io.restassured.RestAssured;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.launcher.container.DatabaseContainers.localDatabaseContainer;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerRequestFixture.createCustomerRequestDocumentAlreadyRegisteredModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerRequestFixture.createCustomerRequestInvalidDocumentModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerRequestFixture.createCustomerRequestInvalidEmailModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerRequestFixture.createCustomerRequestMissingDocumentModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerRequestFixture.createCustomerRequestMissingEmailModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerRequestFixture.createCustomerRequestMissingNameModel;
import static br.com.fiap.tech.challenge.launcher.fixture.CreateCustomerRequestFixture.createCustomerRequestModel;
import static br.com.fiap.tech.challenge.launcher.fixture.Fixture.create;
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
class QueryCustomerResourceIT {

    private static final int LOCAL_PORT = 8689;

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @BeforeAll
    static void beforeAll() {
        System.setProperty("spring.datasource.url", DATABASE.getJdbcUrl());
        System.setProperty("spring.datasource.username", DATABASE.getUsername());
        System.setProperty("spring.datasource.password", DATABASE.getPassword());

        System.setProperty("server.port", String.valueOf(LOCAL_PORT));

        RestAssured.port = LOCAL_PORT;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldBeAbleToGetCustomerByDocument() {
        var document = "32495281885";

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("document", document)
            .when()
                .get("/customer")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CustomerResponseSchema.json"));
    }

    @Test
    void shouldReturnBadRequestWhenGetCustomerWithInvalidDocument() {
        var document = "32497881885";

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("document", document)
            .when()
                .get("/customer")
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturnNoContentWhenGetCustomerUsingDocumentThatDoesNotExists() {
        var document = "48826325197";

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("document", document)
            .when()
                .get("/customer")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}

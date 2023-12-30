package br.com.fiap.tech.challenge.launcher.rest;

import br.com.fiap.tech.challenge.launcher.config.TestConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.fiap.tech.challenge.launcher.container.DatabaseContainers.localDatabaseContainer;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({ "test" })
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class DisableCustomerResourceIT {

    private static final int LOCAL_PORT = 8689;

    @Container
    static MySQLContainer<?> DATABASE = localDatabaseContainer();

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
    void shouldBeAbleToDisableCustomerByDocument() {
        var document = "76633036876";

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .patch("/customer/{document}/disable", document)
            .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/CustomerResponseSchema.json"));
    }

    @Test
    void shouldReturnBadRequestWhenGetCustomerWithInvalidDocument() {
        var document = "32497881885";

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .patch("/customer/{document}/disable", document)
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturnNoContentWhenGetCustomerUsingDocumentThatDoesNotExists() {
        var document = "48826325197";

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .patch("/customer/{document}/disable", document)
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}

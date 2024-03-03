package br.com.fiap.tech.challenge.launcher.rest;

import br.com.fiap.tech.challenge.launcher.config.TestConfiguration;
import org.junit.jupiter.api.Test;
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

import static br.com.fiap.tech.challenge.launcher.container.DatabaseContainers.localDatabaseContainer;
import static br.com.fiap.tech.challenge.launcher.container.LocalStackContainers.localStackContainer;
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
class QueryCustomerResourceIT {

    private static final int LOCAL_PORT = 8689;

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @Container
    protected static GenericContainer<?> LOCAL_STACK_CONTAINER = localStackContainer();

    @DynamicPropertySource
    static void overrideConfig(DynamicPropertyRegistry registry) {
        overrideConfiguration(registry, DATABASE, LOCAL_STACK_CONTAINER);
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

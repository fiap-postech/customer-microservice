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
import static org.hamcrest.Matchers.emptyString;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestConfiguration.class
)
@ActiveProfiles({ "test" })
@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
class QueryCustomerDataRemovalResourceIT {

    @Container
    protected static final MySQLContainer<?> DATABASE = localDatabaseContainer();

    @Container
    protected static GenericContainer<?> LOCAL_STACK_CONTAINER = localStackContainer();

    @DynamicPropertySource
    static void overrideConfig(DynamicPropertyRegistry registry) {
        overrideConfiguration(registry, DATABASE, LOCAL_STACK_CONTAINER);
    }

    @Test
    void shouldGetDataRemoval() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/customer/data/removal/{id}", "c1dc90c3-3e11-4d92-afc6-1702f23e9906")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldAnEmptyResponseWhenUseANonExistsId() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/customer/data/removal/{id}", "34f312ec-be11-4e20-9422-48523024d208")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .body(emptyString());
    }
}

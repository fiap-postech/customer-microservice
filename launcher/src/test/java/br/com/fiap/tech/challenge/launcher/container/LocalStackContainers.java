package br.com.fiap.tech.challenge.launcher.container;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;

import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalStackContainers {

    public static GenericContainer<?> localStackContainer() {
        var image = DockerImageName.parse("fiapsoat2grupo13/localstack-resources:latest");

        var container = new GenericContainer<>(image)
                .withStartupTimeout(Duration.ofSeconds(300))
                .waitingFor(forLogMessage(".*End creating localstack resources.*\\n", 1));

        container.setPortBindings(List.of(
                "4566:4566"
        ));

        return container;
    }

}

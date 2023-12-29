package br.com.fiap.tech.challenge.rest;

import br.com.fiap.tech.challenge.rest.common.handler.ResourceExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
abstract class ResourceBaseTestSuite<T> {

    protected MockMvc mockMvc;

    protected T controller;

    abstract T createController();

    @BeforeEach
    void setUp() {
        controller = createController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ResourceExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }
}

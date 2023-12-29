package br.com.fiap.tech.challenge.adapter.fixture;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.UNKNOWN_ERROR;

public class JsonUtil {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new ApplicationException(UNKNOWN_ERROR, e, e.getMessage());
        }
    }
}
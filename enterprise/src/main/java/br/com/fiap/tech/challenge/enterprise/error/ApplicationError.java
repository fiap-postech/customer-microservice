package br.com.fiap.tech.challenge.enterprise.error;

import br.com.fiap.tech.challenge.exception.error.BaseApplicationError;
import br.com.fiap.tech.challenge.exception.error.ErrorType;

import static br.com.fiap.tech.challenge.exception.error.ErrorType.CONFLICT;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.INTERNAL_SERVER_ERROR;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.INVALID_PARAMETER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public enum ApplicationError implements BaseApplicationError {

    UNKNOWN_ERROR("AE-001", INTERNAL_SERVER_ERROR, TRUE, "Unexpected error [{}]"),
    CUSTOMER_HAS_REGISTRATION("AE-002", CONFLICT, FALSE, "Customer already has registration"),
    CUSTOMER_NOT_FOUND_BY_UUID("AE-003", INVALID_PARAMETER, TRUE, "Customer not found [uuid={}]"),
    CUSTOMER_NOT_FOUND("AE-004", INVALID_PARAMETER, TRUE, "Customer not found"),
    CONSUMER_MAY_NOT_BY_REMOVED("AE-005", INVALID_PARAMETER, TRUE, "Consumer customer may not have his data removed"),
    CUSTOMER_REMOVAL_ALREADY_DONE("AE-006", CONFLICT, TRUE, "Customer data was already removed [uuid={}]"),
    DATA_REMOVAL_REQUEST_NOT_FOUND("AE-007", INVALID_PARAMETER, TRUE, "Customer data removal request not found"),
    CUSTOMER_MAY_NOT_BY_REMOVED("AE-008", INVALID_PARAMETER, TRUE, "Customer may not be removed"),
    ;

    private final String code;

    private final ErrorType errorType;

    private final boolean acceptParameters;

    private final String description;

    ApplicationError(String code, ErrorType errorType, boolean acceptParameters, String description) {
        this.code = code;
        this.errorType = errorType;
        this.acceptParameters = acceptParameters;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public boolean getAcceptParameters() {
        return acceptParameters;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
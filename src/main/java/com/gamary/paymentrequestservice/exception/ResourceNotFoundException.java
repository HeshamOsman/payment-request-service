package com.gamary.paymentrequestservice.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiServiceException {

    /**
     *
     */
    private static final long serialVersionUID = -5706973495569643080L;
    private static final String MESSAGE = "Resource '%s' with identifier '%s' is not found";

    public ResourceNotFoundException(String resourceName, String identifier) {
        super(String.format(MESSAGE, resourceName,identifier));
    }

    @Override
    public HttpStatus getErrorHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}

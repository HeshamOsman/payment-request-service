package com.gamary.paymentrequestservice.exception;

import org.springframework.http.HttpStatus;

public class PostRequestWithIdException  extends ApiServiceException {

    /**
     *
     */
    private static final long serialVersionUID = -5706973495569643080L;
    private static final String MESSAGE = "Post requests for %s can not have identifier";

    public PostRequestWithIdException(String resourceName) {
        super(String.format(MESSAGE, resourceName));
    }

    @Override
    public HttpStatus getErrorHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}

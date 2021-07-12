package com.gamary.paymentrequestservice.config;

import com.gamary.paymentrequestservice.exception.ApiServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler({ RuntimeException.class, ApiServiceException.class })
    public final ResponseEntity<String> handleException(Exception ex) {
        if (ex instanceof ApiServiceException) {
            LOGGER.error("Handling Exception: ", ex);
            return handleApiServiceExceptionAsResponse((ApiServiceException) ex);
        }else {
            LOGGER.error("Unhandled Exception: ", ex);
            return handleNotControlledExceptionAsResponse(ex);
        }
    }

    private ResponseEntity<String> handleApiServiceExceptionAsResponse(ApiServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getErrorHttpStatus());
    }

    private ResponseEntity<String> handleNotControlledExceptionAsResponse(Exception ex) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

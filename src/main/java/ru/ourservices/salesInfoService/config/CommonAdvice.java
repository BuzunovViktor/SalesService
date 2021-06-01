package ru.ourservices.salesInfoService.config;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CommonAdvice {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TypeMismatchException.class})
    protected ResponseEntity<Object> handleTypeMismatchExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(buildErrorMessage(ex, request), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object>  handleIllegalArgumentExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(buildErrorMessage(ex, request), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({HttpClientErrorException.class})
    protected ResponseEntity<Object> handleHttpClientErrorExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(buildErrorMessage(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String buildErrorMessage(Exception ex, WebRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Internal error occurred!\n")
            .append("Exception class: ")
            .append(ex.getClass())
            .append("\n")
            .append("Exception message: ")
            .append(ex.getLocalizedMessage())
            .append("\n");
        for (Throwable th: ex.getSuppressed()) {
            sb.append("Exception message: ")
                .append(th.getLocalizedMessage())
                .append("\n");
        }
        sb.append("Request: ")
            .append(request.toString())
            .append("\n");
        return sb.toString();
    }
}

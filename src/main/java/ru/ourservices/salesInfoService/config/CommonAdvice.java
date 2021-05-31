package ru.ourservices.salesInfoService.config;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class CommonAdvice {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TypeMismatchException.class})
    protected void handleTypeMismatchExceptions(Exception ex, WebRequest request) {
        //nothing to do
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    protected void handleIllegalArgumentExceptions(Exception ex, WebRequest request) {
        //nothing to do
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected void handleAllExceptions(Exception ex, WebRequest request) {
        //todo logging it
        //return new ResponseEntity<>(buildErrorMessage(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
                } catch (DateTimeParseException ex) {
                    IllegalArgumentException illegalArgEx = new IllegalArgumentException(ex.getMessage());
                    illegalArgEx.addSuppressed(ex);
                    throw illegalArgEx;
                }
            }
        });
    }
}

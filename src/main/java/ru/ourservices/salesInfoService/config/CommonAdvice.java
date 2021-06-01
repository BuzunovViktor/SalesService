package ru.ourservices.salesInfoService.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import ru.ourservices.salesInfoService.utils.Utils;

@ControllerAdvice
public class CommonAdvice {
    private static final Logger logger = LoggerFactory.getLogger("Application");

    @ExceptionHandler({TypeMismatchException.class})
    protected ResponseEntity<Object> handleTypeMismatchExceptions(Exception ex, WebRequest request) {
        logger.info(Utils.buildMessage(ex, request));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class,
                       HttpMessageNotReadableException.class,
                       DataIntegrityViolationException.class})
    protected ResponseEntity<Object>  handleIllegalArgumentExceptions(Exception ex, WebRequest request) {
        logger.info(Utils.buildMessage(ex, request));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    protected ResponseEntity<Object> handleHttpClientErrorExceptions(Exception ex, WebRequest request) {
        logger.error(Utils.buildMessage(ex, request));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error(Utils.buildMessage(ex, request));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

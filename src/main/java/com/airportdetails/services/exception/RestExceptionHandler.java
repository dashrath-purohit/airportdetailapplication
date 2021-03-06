package com.airportdetails.services.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String error = "Malformed JSON request";
    return buildResponseEntity(new HandleApiError(HttpStatus.BAD_REQUEST, error, ex));
  }

  private ResponseEntity<Object> buildResponseEntity(HandleApiError handleApiError) {
    return new ResponseEntity<>(handleApiError, handleApiError.getStatus());
  }

  @ExceptionHandler(DetailsNotFoundException.class)
  protected ResponseEntity<Object> handleDetailsNotFoundException(
      DetailsNotFoundException ex) {
    HandleApiError handleApiError = new HandleApiError(INTERNAL_SERVER_ERROR);
    handleApiError.setMessage(ex.getMessage());
    return buildResponseEntity(handleApiError);
  }

}

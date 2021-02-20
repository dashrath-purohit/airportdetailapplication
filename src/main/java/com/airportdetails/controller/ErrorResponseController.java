package com.airportdetails.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.airportdetails.services.exception.HandleApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorResponseController implements ErrorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorResponseController.class);

  private static  final String PAGE_NOT_FOUND = "Page Not Found";
   @GetMapping("/error")
    public ResponseEntity<HandleApiError> errorHandler() {

     LOGGER.info("Something went wrong kindly check the api");
     HandleApiError handleApiError = new HandleApiError(NOT_FOUND);
     handleApiError.setMessage(PAGE_NOT_FOUND);
     return new ResponseEntity<>(handleApiError, handleApiError.getStatus());
    }

    @Override
    public String getErrorPath() {
      return null;
    }

  }

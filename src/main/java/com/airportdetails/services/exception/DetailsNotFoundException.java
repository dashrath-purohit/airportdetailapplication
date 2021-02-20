package com.airportdetails.services.exception;

public class DetailsNotFoundException extends Exception {

  private String message;

  /**
   * Exception for Details not found
   *
   * @param message Message to override in Exception
   */
  public DetailsNotFoundException(String message) {
    this.message = message;
  }

  @Override public String getMessage() {
    return message;
  }

}

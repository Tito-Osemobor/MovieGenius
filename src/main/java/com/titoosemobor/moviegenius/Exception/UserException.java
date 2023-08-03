package com.titoosemobor.moviegenius.Exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserException extends RuntimeException {

  public static class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
      super(message);
    }
  }

  public static class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String message) {
      super(message);
    }
  }

  public static class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
      super(message);
    }
  }

  public static class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String message) {
      super(message);
    }
  }
}

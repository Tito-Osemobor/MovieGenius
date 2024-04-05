package com.titoosemobor.moviegenius.Exception;

public class UserException extends RuntimeException {

  public static class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
      super(message);
    }
  }

  public static class InvalidInformationException extends RuntimeException {
    public InvalidInformationException(String message) {
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

  public static class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String message) {
      super(message);
    }
  }
}

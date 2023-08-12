package com.titoosemobor.moviegenius.Exception;

public class MovieException extends RuntimeException{
  public static class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
      super(message);
    }
  }
}

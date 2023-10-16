package com.titoosemobor.moviegenius.Exception;

public class MovieException extends RuntimeException{
  public static class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
      super(message);
    }
  }
  public static class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(String message) {
      super(message);
    }
  }
}

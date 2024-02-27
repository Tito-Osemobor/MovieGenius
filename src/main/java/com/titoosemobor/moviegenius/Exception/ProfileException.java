package com.titoosemobor.moviegenius.Exception;

public class ProfileException extends RuntimeException {
  public static class NameAlreadyInUse extends RuntimeException {
    public NameAlreadyInUse(String message) {
      super(message);
    }
  }

  public static class MaxNumberOfProfiles extends RuntimeException {
    public MaxNumberOfProfiles(String message) {
      super(message);
    }
  }

  public static class ProfileNotFound extends RuntimeException {
    public ProfileNotFound(String message) {
      super(message);
    }
  }

  public static class ProfilePictureNotFound extends RuntimeException {
    public ProfilePictureNotFound(String message) {
      super(message);
    }
  }
}

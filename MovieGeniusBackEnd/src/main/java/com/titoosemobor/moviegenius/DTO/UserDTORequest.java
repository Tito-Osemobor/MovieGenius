package com.titoosemobor.moviegenius.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTORequest {
  private String email;
  private String password;

  public UserDTORequest(String email) {
    this.email = email;
  }

  public UserDTORequest(String email, String password) {
    this.email = email;
    this.password = password;
  }
}


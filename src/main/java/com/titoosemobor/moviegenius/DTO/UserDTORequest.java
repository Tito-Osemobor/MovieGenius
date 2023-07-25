package com.titoosemobor.moviegenius.DTO;

import com.titoosemobor.moviegenius.Entity.Profile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTORequest {
  private String email;
  private String password;
  private List<Profile> profiles;

  public UserDTORequest(String email, String password) {
    this.email = email;
    this.password = password;
    this.profiles = new ArrayList<>();
  }
}


package com.titoosemobor.moviegenius.DTO;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserProfileDTO {
  private String profile_name;
  private String profile_image;
  public UserProfileDTO(String profile_name, String profile_image) {
    this.profile_name = profile_name;
    this.profile_image = profile_image;
  }
}

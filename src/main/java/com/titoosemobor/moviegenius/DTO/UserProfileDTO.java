package com.titoosemobor.moviegenius.DTO;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class UserProfileDTO {
  private Long id;
  private String profile_name;
  private String profile_image;
  private Timestamp created_at;
  public UserProfileDTO(Long id, String profile_name, String profile_image, Timestamp created_at) {
    this.id = id;
    this.profile_name = profile_name;
    this.profile_image = profile_image;
    this.created_at = created_at;
  }
}

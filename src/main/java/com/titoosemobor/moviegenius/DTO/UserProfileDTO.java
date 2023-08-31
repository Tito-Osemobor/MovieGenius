package com.titoosemobor.moviegenius.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
public class UserProfileDTO {
  private Long id;
  @JsonProperty("profile_name")
  private String profileName;
  @JsonProperty("profile_image")
  private String profileImage;
  @JsonProperty("created_at")
  private Timestamp createdAt;
}

package com.titoosemobor.moviegenius.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
public class UserProfileDTO {
  private Long id;
  private String profileName;
  private String profileImage;
  private Timestamp createdAt;
}

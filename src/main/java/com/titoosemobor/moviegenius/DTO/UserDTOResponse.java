package com.titoosemobor.moviegenius.DTO;

import com.titoosemobor.moviegenius.Entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDTOResponse {
  private String email;
  private Timestamp created_at;
  private List<Profile> profiles;
}

package com.titoosemobor.moviegenius.DataTransferObject;

import com.titoosemobor.moviegenius.Entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
  private Long user_id;
  private String email;
  private Timestamp created_at;
  private List<Profile> profiles;
}

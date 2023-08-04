package com.titoosemobor.moviegenius.DTO;

import com.titoosemobor.moviegenius.Entity.Profile;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserProfileDTOMapper implements Function<Profile, UserProfileDTO> {
  public static final UserProfileDTOMapper INSTANCE = new UserProfileDTOMapper();
  @Override
  public UserProfileDTO apply(Profile profile) {
    return new UserProfileDTO(
      profile.getProfile_name(),
      profile.getProfile_image()
    );
  }
}

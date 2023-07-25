package com.titoosemobor.moviegenius.DTO;

import com.titoosemobor.moviegenius.Entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTOResponse> {
  @Override
  public UserDTOResponse apply(User user) {
    return new UserDTOResponse(
      user.getId(),
      user.getEmail(),
      user.getCreated_at(),
      user.getProfiles()
    );
  }
}

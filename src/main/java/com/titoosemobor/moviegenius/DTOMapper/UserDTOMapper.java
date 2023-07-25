package com.titoosemobor.moviegenius.DTOMapper;

import com.titoosemobor.moviegenius.DataTransferObject.UserDTO;
import com.titoosemobor.moviegenius.Entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
  @Override
  public UserDTO apply(User user) {
    return new UserDTO(
      user.getUser_id(),
      user.getEmail(),
      user.getCreated_at(),
      user.getProfiles()
    );
  }
}

package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.UserDTOMapper;
import com.titoosemobor.moviegenius.DTO.UserDTOResponse;
import com.titoosemobor.moviegenius.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDTOMapper userDTOMapper;

  public List<UserDTOResponse> allUsers() {
    return userRepository.findAll()
      .stream()
      .map(userDTOMapper)
      .collect(Collectors.toList());
  }

  public Optional<UserDTOResponse> userById(Long id) {
    return userRepository.findById(id)
      .map(userDTOMapper);
  }
}

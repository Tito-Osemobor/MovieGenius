package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.PasswordUpdateDTO;
import com.titoosemobor.moviegenius.DTO.UserDTOMapper;
import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserDTOResponse;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Exception.UserException;
import com.titoosemobor.moviegenius.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
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

  public Optional<UserDTOResponse> userByEmailAndPassword(UserDTORequest userDTORequest) {
    return userRepository.findUserByEmailAndPassword(userDTORequest.getEmail(), userDTORequest.getPassword())
      .map(userDTOMapper);
  }

  public Optional<UserDTOResponse> updateUserPassword(PasswordUpdateDTO passwordUpdateDTO) {
    if (passwordUpdateDTO.getCurrentPassword() == null ||
      passwordUpdateDTO.getNewPassword() == null) {
      throw new UserException.InvalidInputException("Enter current and new password");
    }

    if(!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getReNewPassword())) {
      throw new UserException.PasswordMismatchException("New password and re-entered password do not match");
    }
    UserDTORequest userDTORequest = new UserDTORequest(passwordUpdateDTO.getEmail(), passwordUpdateDTO.getCurrentPassword());
    Optional<User> userToUpdateOptional = userRepository.findUserByEmail(userDTORequest.getEmail());
    if (userToUpdateOptional.isEmpty()) {
      throw new UserException.UserNotFoundException("Incorrect email or password");
    }
    if (passwordEncoder.matches(userDTORequest.getPassword(), userToUpdateOptional.get().getPassword())) {
      User userToUpdate = userToUpdateOptional.get();
      userToUpdate.setPassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));
      userRepository.save(userToUpdate);
      UserDTOResponse userDTOResponse = UserDTOMapper.INSTANCE.apply(userToUpdate);
      return Optional.of(userDTOResponse);
    }
    else {
      throw new UserException.InvalidInputException("Incorrect email or password");
    }
  }
}

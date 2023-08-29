package com.titoosemobor.moviegenius.Service;

import com.titoosemobor.moviegenius.DTO.PasswordUpdateDTO;
import com.titoosemobor.moviegenius.DTO.UserDTOMapper;
import com.titoosemobor.moviegenius.DTO.UserDTORequest;
import com.titoosemobor.moviegenius.DTO.UserDTOResponse;
import com.titoosemobor.moviegenius.Entity.User;
import com.titoosemobor.moviegenius.Exception.UserException;
import com.titoosemobor.moviegenius.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserDTOMapper userDTOMapper;
  private final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

  public Optional<UserDTOResponse> authUser(User authUser) {
    if(authUser == null) {
      throw new UserException.UserNotFoundException("User Not Found");
    }
    return Optional.ofNullable(UserDTOMapper.INSTANCE.apply(authUser));
  }

  public Optional<UserDTOResponse> userByEmail(String email) {
    Optional<User> userOptional = userRepository.findUserByEmail(email);
    if (userOptional.isEmpty()) {
      throw new UserException.UserNotFoundException("User not found");
    }
    return userOptional
      .map(userDTOMapper);
  }

  public Optional<UserDTOResponse> updateUserPassword(PasswordUpdateDTO passwordUpdateDTO) {
    if (passwordUpdateDTO.getCurrentPassword().isEmpty()||
      passwordUpdateDTO.getNewPassword().isEmpty()) {
      throw new UserException.InvalidInputException("Enter current and new password");
    }
    if (passwordUpdateDTO.getCurrentPassword().equals(passwordUpdateDTO.getNewPassword())) {
      throw new UserException.PasswordMismatchException("Current and New password must not match");
    }
    if (!isValidPassword(passwordUpdateDTO.getNewPassword(), passwordRegex)) {
      throw new UserException.InvalidInformationException("New Password is invalid");
    }
    if(!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getReNewPassword())) {
      throw new UserException.PasswordMismatchException("New password and re-entered password do not match");
    }
    UserDTORequest userDTORequest = new UserDTORequest(passwordUpdateDTO.getEmail(), passwordUpdateDTO.getCurrentPassword());
    Optional<User> userToUpdateOptional = userRepository.findUserByEmail(userDTORequest.getEmail());
    if (userToUpdateOptional.isEmpty()) {
      throw new UserException.UserNotFoundException("Incorrect email and/or password");
    }
    if (passwordEncoder.matches(userDTORequest.getPassword(), userToUpdateOptional.get().getPassword())) {
      User userToUpdate = userToUpdateOptional.get();
      userToUpdate.setPassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));
      userRepository.save(userToUpdate);
      UserDTOResponse userDTOResponse = UserDTOMapper.INSTANCE.apply(userToUpdate);
      return Optional.of(userDTOResponse);
    }
    else {
      throw new UserException.UserNotFoundException("Incorrect email and/or password");
    }
  }

  public Boolean isValidPassword(String newPassword, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(newPassword);
    return matcher.matches();
  }
}
